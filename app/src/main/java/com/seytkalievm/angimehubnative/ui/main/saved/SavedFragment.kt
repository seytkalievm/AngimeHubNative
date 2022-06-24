package com.seytkalievm.angimehubnative.ui.main.saved


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentSavedBinding
import com.seytkalievm.angimehubnative.models.ArtistPreview
import com.seytkalievm.angimehubnative.models.ShowPreview
import com.seytkalievm.angimehubnative.ui.adapters.ArtistPreviewAdapter
import com.seytkalievm.angimehubnative.ui.adapters.ShowPreviewAdapter
import com.seytkalievm.angimehubnative.ui.main.SessionActivity
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "Saved fragment"

@AndroidEntryPoint
class SavedFragment : Fragment() {

    private val viewModel: SavedViewModel by viewModels()
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as SessionActivity).supportActionBar?.setTitle(R.string.saved_recordings)
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var shows = mutableListOf<ShowPreview>()
        viewModel.shows.observe(viewLifecycleOwner){
            shows = it as MutableList<ShowPreview>
            binding.fragmentSavedShowsRv.adapter = ShowPreviewAdapter(shows)
        }

        binding.apply {
            fragmentSavedShowsRv.adapter = ShowPreviewAdapter(shows)
            fragmentSavedShowsRv.layoutManager = LinearLayoutManager(context)
        }
    }
}