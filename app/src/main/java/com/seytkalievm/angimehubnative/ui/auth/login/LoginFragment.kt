package com.seytkalievm.angimehubnative.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.seytkalievm.angimehubnative.databinding.FragmentLoginBinding
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor(): Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()


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