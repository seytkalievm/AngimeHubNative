package com.seytkalievm.angimehubnative.presentation.session.standup


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentStandUpBinding
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview
import com.seytkalievm.angimehubnative.domain.model.ShowPreview
import com.seytkalievm.angimehubnative.presentation.adapters.ArtistPreviewAdapter
import com.seytkalievm.angimehubnative.presentation.adapters.ShowPreviewAdapter
import com.seytkalievm.angimehubnative.presentation.session.SessionActivity
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

        val onArtistClicked = ArtistPreviewAdapter.OnItemClickListener{ artist ->
            showArtistProfile(artist)
        }

        val onShowClicked = ShowPreviewAdapter.OnItemClickListener{show ->
            playMedia(show)
        }

        viewModel.shows.observe(viewLifecycleOwner){
            shows = it as MutableList<ShowPreview>
            binding.fragmentStandUpShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
        }

        viewModel.artist.observe(viewLifecycleOwner){
            artists = it as MutableList<ArtistPreview>
            binding.fragmentStandUpArtistRv.adapter = ArtistPreviewAdapter(artists, onArtistClicked)
        }

        binding.apply {
            fragmentStandUpShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
            fragmentStandUpShowsRv.layoutManager = LinearLayoutManager(context)
            fragmentStandUpArtistRv.adapter = ArtistPreviewAdapter(artists, onArtistClicked)
            fragmentStandUpArtistRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }
    }

    private fun showArtistProfile(artist: ArtistPreview){
        val id = artist.Id
        val action = StandUpFragmentDirections.actionStandUpFragmentToArtistPageFragment(id)
        (activity as SessionActivity).updateToolbar("About an artist", true)
        findNavController().navigate(action)
    }

    private fun playMedia(show: ShowPreview){
        Toast.makeText(this.context, show.name, Toast.LENGTH_SHORT).show()
    }
}