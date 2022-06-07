package com.seytkalievm.angimehubnative.ui.auth.register

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentRegisterBinding
import java.util.*


class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

            registerSignUpBtn.setOnClickListener {
                viewModel.register()
            }
        }


    }

}