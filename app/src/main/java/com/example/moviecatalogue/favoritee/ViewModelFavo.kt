package com.example.moviecatalogue.favoritee

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.repository.AppRepository
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.tvshow.TvshowPaging
import com.example.moviecatalogue.tvshow.TvshowPopular
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ViewModelFavo @Inject constructor(private val repository: AppRepository): ViewModel() {

   // private val _databaseMovie: LiveData<ArrayList<MovieModel>> = repository.getAllMovieFromDatabase()
    //private val _databaseTVShow: LiveData<ArrayList<TVShowModel>> = repository.getALlTVShowFromDatabase()
    private var _findMovie: LiveData<MovieModel>? = null
    private var _findTVShow: LiveData<TVShowModel>? = null

    fun deleteMovie(movieModel: MovieModel) = viewModelScope.launch { repository.deleteMovie(movieModel ) }

    fun deleteTVShow(tvShowModel: TVShowModel) = viewModelScope.launch { repository.deleteTVShow(tvShowModel) }

    fun findMovie(id: Int): LiveData<MovieModel>? {
        _findMovie = repository.findMovieFromDatabase(id)
        return _findMovie
    }

    fun findTVShow(id: Int): LiveData<TVShowModel>? {
        _findTVShow = repository.findTVShowFromDatabase(id)
        return _findTVShow
    }

    fun getAllMovies(sort: String): LiveData<PagedList<MovieModel>> =
        LivePagedListBuilder(repository.getAllMovieFromDatabase(sort), 10).build()

    fun getAllTVShows(sort: String): LiveData<PagedList<TVShowModel>> =
        LivePagedListBuilder(repository.getALlTVShowFromDatabase(sort), 10).build()
}