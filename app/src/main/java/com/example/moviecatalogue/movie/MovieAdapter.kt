package com.example.moviecatalogue.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalogue.databinding.ActivityListviewBinding
import java.util.ArrayList

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>() {

    private var movies = ArrayList<MovieModel>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setMovies(movieList: ArrayList<MovieModel>) {
        movies.clear()
        movies.addAll(movieList)
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
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class CardViewViewHolder(private val binding: ActivityListviewBinding) : RecyclerView.ViewHolder(binding.root) {
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

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieModel)
    }
}