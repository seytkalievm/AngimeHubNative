package com.seytkalievm.angimehubnative.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

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
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]


        binding.loginSignUpBtn.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
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