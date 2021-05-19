package com.example.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.repository.AppRepository
import com.example.moviecatalogue.repository.Repository
import com.example.moviecatalogue.tvshow.TVShowModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ViewModelDetail @Inject constructor(private val repository: AppRepository) : ViewModel() {

    private lateinit var movieData : LiveData<MovieModel>
    private lateinit var tvShowData : LiveData<TVShowModel>
    private var _findMovie: LiveData<MovieModel>? = null
    private var _findTVShow: LiveData<TVShowModel>? = null

    fun setSelectedMovie(id: Int) { movieData = repository.getDetailMovie(id) }

    fun setSelectedTV(id: Int) { tvShowData = repository.getDetailTV(id) }

    fun getSelectedMovie() : LiveData<MovieModel> = movieData

    fun getSelectedTVShow() : LiveData<TVShowModel> = tvShowData

    fun insertmovie(movie: MovieModel) = viewModelScope.launch { repository.insert(movie) }

    fun inserttv(tvShow: TVShowModel) = viewModelScope.launch { repository.insert(tvShow) }

    fun deleteMovie(movie: MovieModel) = viewModelScope.launch { repository.deleteMovie(movie) }

    fun deleteTVShow(tvShow: TVShowModel) = viewModelScope.launch { repository.deleteTVShow(tvShow) }

    fun findMovie(id: Int): LiveData<MovieModel>? {
        _findMovie = repository.findMovieFromDatabase(id)
        return _findMovie
    }

    fun findTvshow (id: Int): LiveData<TVShowModel>? {
        _findTVShow = repository.findTVShowFromDatabase(id)
        return _findTVShow
    }
}