package com.seytkalievm.angimehubnative.ui.main.podcast


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentPodcastsBinding
import com.seytkalievm.angimehubnative.models.ArtistPreview
import com.seytkalievm.angimehubnative.models.Show
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.ui.adapters.ArtistPreviewAdapter
import com.seytkalievm.angimehubnative.ui.adapters.ShowPreviewAdapter
import com.seytkalievm.angimehubnative.ui.main.SessionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PodcastsFragment : Fragment() {

    private val viewModel: PodcastsViewModel by viewModels()
    private lateinit var binding: FragmentPodcastsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as SessionActivity).supportActionBar?.setTitle(R.string.podcasts)
        binding = FragmentPodcastsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onArtistClicked = ArtistPreviewAdapter.OnItemClickListener{artist ->
            showArtistProfile(artist)
        }

        val onShowClicked = ShowPreviewAdapter.OnItemClickListener{show ->
            playMedia(show)
        }

        var shows = mutableListOf<ShowPreview>()
        var artists = mutableListOf<ArtistPreview>()
        viewModel.shows.observe(viewLifecycleOwner){
            shows = it as MutableList<ShowPreview>
            binding.fragmentPodcastsShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
        }

        viewModel.artist.observe(viewLifecycleOwner){
            artists = it as MutableList<ArtistPreview>
            binding.fragmentPodcastsArtistRv.adapter = ArtistPreviewAdapter(artists, onArtistClicked)
        }

        binding.apply {
            fragmentPodcastsShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
            fragmentPodcastsShowsRv.layoutManager = LinearLayoutManager(context)

            fragmentPodcastsArtistRv.adapter = ArtistPreviewAdapter(artists, onArtistClicked)
            fragmentPodcastsArtistRv.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }

    }

    private fun showArtistProfile(artist: ArtistPreview){
        Toast.makeText(this.context, artist.getName(), Toast.LENGTH_SHORT).show()
    }

    private fun playMedia(show: ShowPreview){
        Toast.makeText(this.context, show.name, Toast.LENGTH_SHORT).show()
    }

}