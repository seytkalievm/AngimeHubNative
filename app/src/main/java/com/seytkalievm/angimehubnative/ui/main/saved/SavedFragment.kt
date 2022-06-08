package com.seytkalievm.angimehubnative.ui.main.saved


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.seytkalievm.angimehubnative.R
import com.seytkalievm.angimehubnative.databinding.FragmentSavedBinding
import com.seytkalievm.angimehubnative.ui.main.MainActivity

const val TAG = "Saved fragment"
class SavedFragment : Fragment() {

    private lateinit var viewModel: SavedViewModel
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as MainActivity).supportActionBar?.setTitle(R.string.saved_recordings)
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i("Saved", "OnAttach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Saved", "onDestroy")

    }

    override fun onDetach() {
        super.onDetach()
        Log.i("Saved", "onDetach")

    }
}