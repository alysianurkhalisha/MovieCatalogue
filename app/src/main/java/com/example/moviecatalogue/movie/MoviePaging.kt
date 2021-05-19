package com.example.moviecatalogue.movie

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityListviewBinding

class MoviePaging: PagedListAdapter<MovieModel, MoviePaging.MovieViewHolder>(DIFF_CALLBACK) {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePaging.MovieViewHolder {
        val binding = ActivityListviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position) as MovieModel)
    }

    inner class MovieViewHolder(private val binding: ActivityListviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieModel){
            with(binding){
                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185" + movie.pict)
                    .into(imagephoto)
                title.text = movie.title
                titlerating.text = movie.rating.toString()


                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<MovieModel> = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldData: MovieModel, newData: MovieModel): Boolean {
                return oldData.title == newData.title && oldData.description == newData.description
            }
            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldData: MovieModel, newData: MovieModel): Boolean {
                return oldData == newData
            }
        }
    }
}