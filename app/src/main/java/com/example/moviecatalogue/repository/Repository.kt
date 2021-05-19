package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel

interface Repository {
    fun getPopularMovies(): LiveData<ArrayList<MovieModel>>

    fun getPopularTv(): LiveData<ArrayList<TVShowModel>>

    fun getDetailMovie(id : Int) : LiveData<MovieModel>

    fun getDetailTV(id : Int) : LiveData<TVShowModel>

    fun getAllMovieFromDatabase(sort: String) : DataSource.Factory<Int, MovieModel>

    fun getALlTVShowFromDatabase(sort: String) : DataSource.Factory<Int, TVShowModel>


    fun findMovieFromDatabase(id: Int) : LiveData<MovieModel>?

    fun findTVShowFromDatabase(id: Int) : LiveData<TVShowModel>?

    suspend fun deleteMovie(movie: MovieModel)

    suspend fun deleteTVShow(tvShow: TVShowModel)

    suspend fun insert(movie: MovieModel)

    suspend fun insert(tvShow: TVShowModel)
}