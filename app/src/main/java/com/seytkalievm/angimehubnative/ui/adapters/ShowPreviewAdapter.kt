package com.seytkalievm.angimehubnative.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.seytkalievm.angimehubnative.databinding.ShowPreviewBinding
import com.seytkalievm.angimehubnative.models.ArtistPreview
import com.seytkalievm.angimehubnative.models.ShowPreview

class ShowPreviewAdapter(
    private val shows: List<ShowPreview>,
    private val onClickListener: OnItemClickListener
    ) : RecyclerView.Adapter<ShowPreviewAdapter.ShowViewHolder>() {

    inner class ShowViewHolder(private val binding: ShowPreviewBinding):
        RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(show: ShowPreview){
            binding.show = show
            binding.previewAddToFavouriteBtn.setOnClickListener {
                Toast.makeText(it.context, "Added to favourites", Toast.LENGTH_SHORT).show()
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onClickListener.onClick(shows[adapterPosition])
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

    class OnItemClickListener(val clickListener: (show: ShowPreview) -> Unit){
        fun onClick(show: ShowPreview) = clickListener(show)
    }
}