package com.example.moviecatalogue

import android.os.Handler
import android.os.Looper
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.utils.ResourceEspress

class RemoteDataFake private constructor(private val dataDummy: DataTest) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1500

        @Volatile
        private var instance: RemoteDataFake? = null

        fun getInstance(data: DataTest): RemoteDataFake =
            instance ?: synchronized(this) {
                RemoteDataFake(data).apply { instance = this }
            }
    }

    fun getPopularMovies(callback: LoadMoviesCallback) {
        ResourceEspress.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(dataDummy.generateDataMovies() )
            ResourceEspress.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getPopularTVShow(callback: LoadTVShowCallback) {
        ResourceEspress.increment()
        handler.postDelayed({
            callback.onAllTVShowReceived(dataDummy.generateDataTVShow())
            ResourceEspress.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getMovieContent(moduleId: Int, callback: LoadMovieContentCallback) {
        ResourceEspress.increment()
        handler.postDelayed({
            lateinit var data : MovieModel
            dataDummy.generateDataMovies().forEach { movie ->
                if ( movie.id == moduleId) {
                    data = movie
                }
            }
            callback.onContentReceived(data)
            ResourceEspress.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getTVContent(moduleId: Int, callback: LoadTVContentCallback) {
        ResourceEspress.increment()
        handler.postDelayed({
            lateinit var data : TVShowModel
            dataDummy.generateDataTVShow().forEach { tv ->
                if ( tv.id == moduleId) {
                    data = tv
                }
            }
            callback.onContentReceived(data)
            ResourceEspress.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponses: List<MovieModel>)
    }
    interface LoadMovieContentCallback {
        fun onContentReceived(movieResponse: MovieModel)
    }

    interface LoadTVShowCallback {
        fun onAllTVShowReceived(tvshowResponses: List<TVShowModel>)
    }
    interface LoadTVContentCallback {
        fun onContentReceived(tvshowResponse: TVShowModel)
    }

}
