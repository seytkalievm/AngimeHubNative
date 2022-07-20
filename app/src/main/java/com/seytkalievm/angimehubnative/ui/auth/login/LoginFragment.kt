package com.seytkalievm.angimehubnative.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentLoginBinding
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import com.seytkalievm.angimehubnative.ui.auth.register.RegisterFragment
import dagger.hilt.android.AndroidEntryPoint
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
        var toast: Toast? = null
        val navController = this.findNavController()

        viewModel.user.observe(viewLifecycleOwner){
            if (it != null) (activity as AuthActivity).startSession()
        }

        viewModel.emptyEmail.observe(viewLifecycleOwner){
            if (it) binding.loginEmailLayout.error = getString(R.string.empty_email)
        }

        viewModel.emptyPassword.observe(viewLifecycleOwner){
            if (it) binding.loginPasswordLayout.error = getString(R.string.empty_password)
        }

        viewModel.error.observe(viewLifecycleOwner){
            toast?.cancel()
            toast = Toast.makeText(context,getString(it), Toast.LENGTH_SHORT)
            toast?.show()
        }

        binding.loginSignUpBtn.setOnClickListener{
            navController.navigate(R.id.registerFragment2)
        }

        binding.loginEmailEt.addTextChangedListener{
            binding.loginEmailLayout.error = null
            viewModel.credentialsChanged(email = it.toString(), null)
        }

        binding.loginPasswordEt.addTextChangedListener {
            binding.loginPasswordLayout.error = null
            viewModel.credentialsChanged(null, password = it.toString())
        }

        binding.loginSignInBtn.setOnClickListener {
            viewModel.logIn()
        }
    }
}