package com.seytkalievm.angimehubnative.ui.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.seytkalievm.angimehubnative.databinding.FragmentProfileBinding
import com.seytkalievm.angimehubnative.ui.auth.AuthActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment @Inject constructor(): Fragment() {

    lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.deleted.observe(viewLifecycleOwner){ deleted ->
            if (deleted){
                val intent = Intent(activity, AuthActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        }

        binding.apply {
            viewmodel = viewModel
            profileSignOutBtn.setOnClickListener{
                viewModel.logout()
            }
        }

    }
}