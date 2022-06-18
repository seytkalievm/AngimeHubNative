package com.seytkalievm.angimehubnative.ui.main.podcast


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentPodcastsBinding
import com.seytkalievm.angimehubnative.ui.main.SessionActivity

class PodcastsFragment : Fragment() {

    private lateinit var viewModel: PodcastsViewModel
    private lateinit var binding: FragmentPodcastsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as SessionActivity).supportActionBar?.setTitle(R.string.podcasts)
        binding = FragmentPodcastsBinding.inflate(inflater, container, false)
        return binding.root
    }

}