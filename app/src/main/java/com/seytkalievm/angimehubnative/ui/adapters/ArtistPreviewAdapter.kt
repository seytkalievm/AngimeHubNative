package com.seytkalievm.angimehubnative.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.angimehubnative.databinding.ArtistCardBinding
import com.seytkalievm.angimehubnative.models.ArtistPreview

class ArtistPreviewAdapter(private val artists: List<ArtistPreview>)
    : RecyclerView.Adapter<ArtistPreviewAdapter.ArtistViewHolder>() {
    inner class ArtistViewHolder(private val binding: ArtistCardBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(artist: ArtistPreview){
            binding.artist = artist
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ArtistCardBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    override fun getItemCount(): Int {
        return artists.size
    }


}