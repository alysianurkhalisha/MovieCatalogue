package com.example.moviecatalogue

import android.graphics.Movie
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.moviecatalogue.R
import com.example.moviecatalogue.ViewModelDetail
import com.example.moviecatalogue.databinding.ActivityDetailBinding
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA= "EXTRA"
        const val EXTRA_SELECT = "EXTRA_SELECT"
        const val EXTRA_MOVIES = 100
        const val EXTRA_TV = 101
    }
    private lateinit var binding: ActivityDetailBinding
    private val checked: Int = R.drawable.ic_favorite
    private val unChecked: Int = R.drawable.ic_favoriteborder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f

        val viewModel :  ViewModelDetail by viewModels()
        var statusFav = false

        var id = 0
        var select: Int = 0
        var movieData: MovieModel = MovieModel(id)
        var tvShowData: TVShowModel= TVShowModel(id)

        if (intent.extras != null) {

            id = intent.getIntExtra(EXTRA, 0)
            select = intent.getIntExtra(EXTRA_SELECT, 0)

            showLoading(true)

            when (select) {
                EXTRA_MOVIES -> {
                    setActionBarTittle("Movie")
                    viewModel.findMovie(id)?.observe(this, { movie ->
                        if (movie != null){
                            movieData = movie
                            bindViewMovie(movie)
                            statusFav = true
                            binding.buttonFavorite.setImageResource(checked)
                            showLoading(false)
                        } else {
                            viewModel.setSelectedMovie(id)
                            viewModel.getSelectedMovie().observe(this, { movieDetail ->
                                movieData = movieDetail
                                bindViewMovie(movieDetail)
                                binding.buttonFavorite.setImageResource(unChecked)
                                showLoading(false)
                            })
                        }
                    })
                }
                EXTRA_TV -> {
                    setActionBarTittle("Tv Show")
                    viewModel.findTvshow(id)?.observe(this, { tvShow ->
                        if (tvShow != null){
                            tvShowData = tvShow
                            bindViewTvShows(tvShow)
                            statusFav = true
                            binding.buttonFavorite.setImageResource(checked)
                            showLoading(false)
                        } else {
                            viewModel.setSelectedTV(id)
                            viewModel.getSelectedTVShow().observe(this, { tvDetail ->
                                tvShowData = tvDetail
                                bindViewTvShows(tvDetail)
                                binding.buttonFavorite.setImageResource(unChecked)
                                showLoading(false)
                            })
                        }
                    })
                }
                else -> return
            }
        }
        binding.buttonFavorite.setOnClickListener { view ->
            if (!statusFav) {
                when(select){
                    EXTRA_MOVIES -> viewModel.insertmovie(movieData)
                    EXTRA_TV -> viewModel.inserttv(tvShowData)
                    else -> return@setOnClickListener
                }
                binding.buttonFavorite.setImageResource(checked)
                Snackbar.make(view, getString(R.string.add_favo), Snackbar.LENGTH_SHORT).show()
            } else {
                when(select){
                    EXTRA_MOVIES -> viewModel.deleteMovie(movieData)
                    EXTRA_TV -> viewModel.deleteTVShow(tvShowData)
                    else -> return@setOnClickListener
                }
                binding.buttonFavorite.setImageResource(unChecked)
                Snackbar.make(view, getString(R.string.remove_favo), Snackbar.LENGTH_SHORT).show()
            }
            statusFav = !statusFav
            binding.dtGenre.text=""
        }
    }

    private fun bindViewMovie(movie: MovieModel) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w185" + movie.pict)
                .into(dtImage)
            dtTitle.text = movie.title
            dtRating.text = movie.rating.toString()
            dtRelease.text = movie.release
            for (i in movie.genre?.indices!!) {
                if (i < movie.genre.size - 1) {
                    dtGenre.append("${movie.genre[i].name}, ")
                } else {
                    dtGenre.append(movie.genre[i].name)
                }
            }
            dtDesc.text = movie.description
        }
    }

    private fun bindViewTvShows(tvShows: TVShowModel) {
        with(binding) {
            Glide.with(this@DetailActivity)
                .load("https://image.tmdb.org/t/p/w185" + tvShows.pict)
                .into(dtImage)
            dtTitle.text = tvShows.title
            dtRating.text = tvShows.rating.toString()
            dtRelease.text = tvShows.release
            for (i in tvShows.genre?.indices!!) {
                if (i < tvShows.genre.size - 1) {
                    dtGenre.append("${tvShows.genre[i].name}, ")
                } else {
                    dtGenre.append(tvShows.genre[i].name)
                }
            }
            dtDesc.text = tvShows.description
        }
    }

    private fun setActionBarTittle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressbar.visibility = View.VISIBLE
        } else {
            binding.progressbar.visibility = View.GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}