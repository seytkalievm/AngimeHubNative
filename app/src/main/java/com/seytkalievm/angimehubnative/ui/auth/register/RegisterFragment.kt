package com.seytkalievm.angimehubnative.ui.auth.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.seytkalievm.angimehubnative.databinding.FragmentRegisterBinding
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped

import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment @Inject constructor(): Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("RegisterFragment", this.toString())

        viewModel.formState.observe(viewLifecycleOwner){
            if (it.firstNameError != null){
                binding.registerFirstNameLayout.error = getString(it.firstNameError!!)
            }

            if (it.firstNameError != null){
                binding.registerFirstNameLayout.error = getString(it.firstNameError!!)
            }
            if(it.emailError != null){
                binding.registerEmailLayout.error = getString(it.emailError!!)
            }
            if(it.passwordError != null){
                binding.registerPasswordLayout.error = getString(it.passwordError!!)
            }
            if (it.confPasswordError!=null){
                binding.registerConfPasswordLayout.error  = getString(it.confPasswordError!!)
            }
        }

        binding.apply {
            registerFirstNameEt.addTextChangedListener {
                registerFirstNameLayout.error = null
                viewModel.credentialsChanged(firstName = it.toString().trim())
            }
            registerSecondNameEt.addTextChangedListener{
                registerSecondNameLayout.error = null
                viewModel.credentialsChanged(secondName = it.toString().trim())
            }
            registerEmailEt.addTextChangedListener {
                registerEmailLayout.error = null
                viewModel.credentialsChanged(email = it.toString().trim())
            }
            registerPasswordEt.addTextChangedListener {
                registerPasswordLayout.error = null
                viewModel.credentialsChanged(password = it.toString())
            }
            registerConfPasswordEt.addTextChangedListener {
                registerConfPasswordLayout.error = null
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

}