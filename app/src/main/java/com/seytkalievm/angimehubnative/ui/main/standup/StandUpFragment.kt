package com.seytkalievm.angimehubnative.ui.main.standup


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentStandUpBinding
import com.seytkalievm.angimehubnative.models.ArtistPreview
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.ui.adapters.ArtistPreviewAdapter
import com.seytkalievm.angimehubnative.ui.adapters.ShowPreviewAdapter
import com.seytkalievm.angimehubnative.ui.main.SessionActivity
import dagger.hilt.android.AndroidEntryPoint

const val TAG = "Stand up fragment"

@AndroidEntryPoint
class StandUpFragment : Fragment() {

    private val viewModel: StandUpViewModel by viewModels()
    private lateinit var binding: FragmentStandUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as SessionActivity).supportActionBar?.setTitle(R.string.stand_up_shows)
        binding = FragmentStandUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var shows = mutableListOf<ShowPreview>()
        var artists = mutableListOf<ArtistPreview>()
        viewModel.shows.observe(viewLifecycleOwner){
            shows = it as MutableList<ShowPreview>
            binding.fragmentStandUpShowsRv.adapter = ShowPreviewAdapter(shows)
        }

        viewModel.artist.observe(viewLifecycleOwner){
            artists = it as MutableList<ArtistPreview>
            binding.fragmentStandUpArtistRv.adapter = ArtistPreviewAdapter(artists)
        }

        binding.apply {
            fragmentStandUpShowsRv.adapter = ShowPreviewAdapter(shows)
            fragmentStandUpShowsRv.layoutManager = LinearLayoutManager(context)

            fragmentStandUpArtistRv.adapter = ArtistPreviewAdapter(artists)
            fragmentStandUpArtistRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }
}