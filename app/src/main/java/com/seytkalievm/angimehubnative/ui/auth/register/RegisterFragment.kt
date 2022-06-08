package com.seytkalievm.angimehubnative.ui.auth.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.seytkalievm.angimehubnative.databinding.FragmentRegisterBinding
import com.seytkalievm.angimehubnative.di.viewmodel.ViewModelFactory
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import java.util.*
import javax.inject.Inject


class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object{
        fun newInstance() = RegisterFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as MyApplication).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[RegisterViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("RegisterFragment", this.toString())

        binding.apply {
            registerFirstNameEt.addTextChangedListener {
                viewModel.credentialsChanged(firstName = it.toString().trim())
            }
            registerSecondNameEt.addTextChangedListener{
                viewModel.credentialsChanged(secondName = it.toString().trim())
            }
            registerEmailEt.addTextChangedListener {
                viewModel.credentialsChanged(email = it.toString().trim())
            }
            registerPasswordEt.addTextChangedListener {
                viewModel.credentialsChanged(password = it.toString())
            }
            registerConfPasswordEt.addTextChangedListener {
                viewModel.credentialsChanged(confPassword = it.toString())
            }

            registerSignInBtn.setOnClickListener{
                (activity as AuthActivity).goToLogin()
            }

            registerSignUpBtn.setOnClickListener {
                viewModel.register()
            }
        }

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("RegisterFragment", "onDetach")
    }

}