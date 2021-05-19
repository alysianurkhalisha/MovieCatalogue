package com.example.moviecatalogue.tvshow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityListviewBinding

class TvshowPaging: PagedListAdapter<TVShowModel, TvshowPaging.TVShowViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TVShowModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvshowPaging.TVShowViewHolder {
        val binding = ActivityListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TVShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TVShowViewHolder, position: Int) {
        holder.bind(getItem(position) as TVShowModel)
    }

    inner class TVShowViewHolder(private val binding: ActivityListviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(TVShow: TVShowModel){
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + TVShow.pict)
                    .into(imagephoto)
                title.text = TVShow.title
                //titlerating.text = TVShow.rating.toString()


                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(TVShow)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<TVShowModel> = object : DiffUtil.ItemCallback<TVShowModel>() {
            override fun areItemsTheSame(oldData: TVShowModel, newData: TVShowModel): Boolean {
                return oldData.title == newData.title && oldData.description == newData.description
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldData: TVShowModel, newData: TVShowModel): Boolean {
                return oldData == newData
            }
        }
    }
}