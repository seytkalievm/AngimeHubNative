package com.seytkalievm.angimehubnative.ui.main.standup


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentStandUpBinding
import com.seytkalievm.angimehubnative.ui.main.SessionActivity

const val TAG = "Stand up fragment"
class StandUpFragment : Fragment() {

    private lateinit var viewModel: StandUpViewModel
    private lateinit var binding: FragmentStandUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(TAG, "OnCreateView")
        (activity as SessionActivity).supportActionBar?.setTitle(R.string.stand_up_shows)
        binding = FragmentStandUpBinding.inflate(inflater, container, false)
        return binding.root
    }


}