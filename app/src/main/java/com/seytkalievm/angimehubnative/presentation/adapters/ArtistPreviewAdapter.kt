package com.seytkalievm.angimehubnative.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.angimehubnative.databinding.ArtistCardBinding
import com.seytkalievm.angimehubnative.domain.model.ArtistPreview

class ArtistPreviewAdapter(
    private val artists: List<ArtistPreview>,
    private val onClickListener: OnItemClickListener
) : RecyclerView.Adapter<ArtistPreviewAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(private val binding: ArtistCardBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(artist: ArtistPreview){
            binding.artist = artist
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onClickListener.onClick(artists[adapterPosition])
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

    class OnItemClickListener(val clickListener: (artist: ArtistPreview) -> Unit){
        fun onClick(artist: ArtistPreview) = clickListener(artist)
    }

}