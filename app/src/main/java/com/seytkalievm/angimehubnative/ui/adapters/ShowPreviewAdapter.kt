package com.seytkalievm.angimehubnative.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.angimehubnative.databinding.ShowPreviewBinding
import com.seytkalievm.angimehubnative.models.ShowPreview

class ShowPreviewAdapter(
    private val shows: List<ShowPreview>
    ) : RecyclerView.Adapter<ShowPreviewAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(private val binding: ShowPreviewBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(show: ShowPreview){
            binding.show = show
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val binding = ShowPreviewBinding.inflate(LayoutInflater
            .from(parent.context), parent, false)
        return ShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount(): Int {
        return shows.size
    }

}