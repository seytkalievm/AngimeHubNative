package com.seytkalievm.angimehubnative.presentation.session.artistpage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.databinding.FragmentArtistPageBinding
import com.seytkalievm.angimehubnative.domain.model.ShowPreview
import com.seytkalievm.angimehubnative.presentation.adapters.ShowPreviewAdapter
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "ArtistPageFragment"
@AndroidEntryPoint
class ArtistPageFragment : Fragment() {

    private lateinit var binding: FragmentArtistPageBinding
    private val viewModel: ArtistPageViewModel by viewModels()
    val args: ArtistPageFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArtistPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArtist(args.artistId)

        var standUps = mutableListOf<ShowPreview>()
        var podcasts = mutableListOf<ShowPreview>()

        val onShowClicked = ShowPreviewAdapter.OnItemClickListener{ show ->
            playMedia(show)
        }

        viewModel.artist.observe(viewLifecycleOwner){
            binding.artist = it
        }

        viewModel.popularPodcasts.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.artistProfileFragmentPodcastsBar.visibility = View.GONE
            } else {
                podcasts = it as MutableList
            }
            binding.artistProfileFragmentPodcastsRv.adapter = ShowPreviewAdapter(podcasts, onShowClicked)
        }

        viewModel.popularStandUps.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                binding.artistProfileFragmentStandUpsBar.visibility = View.GONE
            } else {
                standUps = it as MutableList
            }
            binding.artistProfileFragmentStandUps.adapter = ShowPreviewAdapter(standUps, onShowClicked)
        }

        binding.apply {
            artistProfileFragmentPodcastsRv.adapter = ShowPreviewAdapter(podcasts, onShowClicked)
            artistProfileFragmentPodcastsRv.layoutManager = LinearLayoutManager(context)

            artistProfileFragmentStandUps.adapter = ShowPreviewAdapter(standUps, onShowClicked)
            artistProfileFragmentStandUps.layoutManager = LinearLayoutManager(context)
        }

    }

    private fun playMedia(show: ShowPreview){
        Toast.makeText(this.context, show.name, Toast.LENGTH_SHORT).show()
    }
}