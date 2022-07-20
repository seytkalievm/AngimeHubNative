package com.seytkalievm.angimehubnative.presentation.session.saved


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentSavedBinding
import com.seytkalievm.angimehubnative.domain.model.ShowPreview
import com.seytkalievm.angimehubnative.presentation.adapters.ShowPreviewAdapter
import com.seytkalievm.angimehubnative.presentation.session.SessionActivity
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

        val onShowClicked = ShowPreviewAdapter.OnItemClickListener{show ->
            playMedia(show)
        }
        viewModel.shows.observe(viewLifecycleOwner){
            shows = it as MutableList<ShowPreview>
            binding.fragmentSavedShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
        }

        binding.apply {
            fragmentSavedShowsRv.adapter = ShowPreviewAdapter(shows, onShowClicked)
            fragmentSavedShowsRv.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun playMedia(show: ShowPreview){
        Toast.makeText(this.context, show.name, Toast.LENGTH_SHORT).show()
    }
}