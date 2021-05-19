package com.example.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.repository.AppRepository
import com.example.moviecatalogue.repository.Repository
import com.example.moviecatalogue.tvshow.TVShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(val repository: AppRepository) : ViewModel() {
    private var movieData: LiveData<ArrayList<MovieModel>> = repository.getPopularMovies()
    private var tvShowData: LiveData<ArrayList<TVShowModel>> = repository.getPopularTv()

    fun getMovie() : LiveData<ArrayList<MovieModel>> = movieData
    fun getTv() : LiveData<ArrayList<TVShowModel>> = tvShowData
}