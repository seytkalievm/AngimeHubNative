package com.seytkalievm.angimehubnative.ui.auth.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seytkalievm.angimehubnative.MyApplication
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentLoginBinding
import com.seytkalievm.angimehubnative.di.viewmodel.ViewModelFactory
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import javax.inject.Inject


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginSignUpBtn.setOnClickListener{
            (activity as AuthActivity).goToRegister()
        }

        viewModel.canLogin.observe(viewLifecycleOwner){
            binding.loginSignInBtn.isEnabled = it
        }

        binding.loginEmailEt.addTextChangedListener{
            viewModel.credentialsChanged(email = it.toString(), null)
        }

        binding.loginPasswordEt.addTextChangedListener {
            viewModel.credentialsChanged(null, password = it.toString())
        }

        binding.loginSignInBtn.setOnClickListener {
            viewModel.logIn()
        }
    }
}