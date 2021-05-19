package com.example.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.repository.Repository
import com.example.moviecatalogue.tvshow.TVShowModel

class RepositoryFake (private val RemoteDataFake: RemoteDataFake) : Repository {

    companion object {
        @Volatile
        private var instance: RepositoryFake? = null

        fun getInstance(remoteData: RemoteDataFake): RepositoryFake =
            instance ?: synchronized(this) {
                RepositoryFake(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<ArrayList<MovieModel>> {
        val movieResults = MutableLiveData<ArrayList<MovieModel>>()
        RemoteDataFake.getPopularMovies(object : RemoteDataFake.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieModel>) {
                val movieList = ArrayList<MovieModel>()
                for (response in movieResponses) {
                    val movie = MovieModel(response.id,
                        null,
                        response.title,
                        response.rating,
                        response.release,
                        response.genre,
                        response.description)
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }


    override fun getPopularTv(): LiveData<ArrayList<TVShowModel>> {
        val tvshowResults = MutableLiveData<ArrayList<TVShowModel>>()
        RemoteDataFake.getPopularTVShow(object : RemoteDataFake.LoadTVShowCallback {
            override fun onAllTVShowReceived(tvshowResponses: List<TVShowModel>) {
                val tvshowList = ArrayList<TVShowModel>()
                for (response in tvshowResponses) {
                    val tvShow = TVShowModel(response.id,
                        null,
                        response.title,
                        response.rating,
                        response.release,
                        response.episodes,
                        response.genre,
                        response.description)
                    tvshowList.add(tvShow)
                }
                tvshowResults.postValue(tvshowList)
            }
        })

        return tvshowResults
    }

    override fun getDetailMovie(id: Int): LiveData<MovieModel> {
        val movieResult = MutableLiveData<MovieModel>()
        RemoteDataFake.getMovieContent(id, object : RemoteDataFake.LoadMovieContentCallback{
            override fun onContentReceived(movieResponse: MovieModel) {
                movieResult.postValue(movieResponse)
            }

        })
        return movieResult
    }

    override fun getDetailTV(id: Int): LiveData<TVShowModel> {
        val tvshowResults = MutableLiveData<TVShowModel>()
        RemoteDataFake.getTVContent(id, object : RemoteDataFake.LoadTVContentCallback{
            override fun onContentReceived(tvshowResponse: TVShowModel) {
                tvshowResults.postValue(tvshowResponse)
            }

        })

        return tvshowResults
    }
}

