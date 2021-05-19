package com.example.moviecatalogue.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityListviewBinding
import java.util.ArrayList

class TvshowAdapter : RecyclerView.Adapter<TvshowAdapter.CardViewViewHolder>() {

    private var tvShows = ArrayList<TVShowModel>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setTvshow(tvShowList: ArrayList<TVShowModel>) {
        tvShows.clear()
        tvShows.addAll(tvShowList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding = ActivityListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(tvShows[position])
    }

    override fun getItemCount(): Int = tvShows.size

    inner class CardViewViewHolder(private val binding: ActivityListviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TVShowModel){
            with(binding){
               Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + tvShow.pict)
                    .into(imagephoto)
                title.text = tvShow.title
                titlerating.text = tvShow.rating.toString()

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(tvShow)
                }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TVShowModel)
    }
}