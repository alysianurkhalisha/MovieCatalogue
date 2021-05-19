package com.example.moviecatalogue.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.movie.MoviePopular
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.tvshow.TvshowPopular
import com.example.moviecatalogue.utils.RequestApi
import com.example.moviecatalogue.utils.ResourceEspress
import com.example.moviecatalogue.utils.Retrofit
import com.example.moviecatalogue.utils.Sort
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val appDAO: DAO,
    private val apiRequest: RequestApi
) : Repository{

    val API_KEY = "95f06bf2ea0fc0587509a855857b34e1"

    private val _responseMoviePopular = MutableLiveData<ArrayList<MovieModel>>()
    private val _responseTVPopular = MutableLiveData<ArrayList<TVShowModel>>()
    private val _responseDetailMovie = MutableLiveData<MovieModel>()
    private val _responseDetailTV = MutableLiveData<TVShowModel>()

  //  private val _movieDatabase = appDAO.getAllMovieFromDatabase()
 //   private val _tvShowDatabase = appDAO.getAllTVShowFromDatabase()
    private var _findMovieDatabase: LiveData<MovieModel>? = null
    private var _findTVShowDatabase: LiveData<TVShowModel>? = null

    override fun getPopularMovies() : LiveData<ArrayList<MovieModel>>{
        ResourceEspress.increment()
        apiRequest.getPopularMovies(API_KEY).enqueue(object : Callback<MoviePopular> {
            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                if (response.isSuccessful) {
                    _responseMoviePopular.postValue(response.body()?.hasil)

                    if (! ResourceEspress.getResourceEspressid().isIdleNow) {
                        ResourceEspress.decrement()
                    }

                }
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                Log.e("getPopularMovies: ", t.message.toString())
            }

        })
        return _responseMoviePopular
    }

    override fun getPopularTv(): LiveData<ArrayList<TVShowModel>> {
        ResourceEspress.increment()
        apiRequest.getPopularTVs(API_KEY).enqueue(object : Callback<TvshowPopular> {
            override fun onResponse(call: Call<TvshowPopular>, response: Response<TvshowPopular>) {
                if (response.isSuccessful) {
                    _responseTVPopular.postValue(response.body()?.hasil)

                    if (!ResourceEspress.getResourceEspressid().isIdleNow) {
                        ResourceEspress.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TvshowPopular>, t: Throwable) {
                Log.e("getPopularTVs: ", t.message.toString())
            }

        })

        return _responseTVPopular
    }

    override fun getDetailMovie(id : Int) : LiveData<MovieModel> {
        ResourceEspress.increment()
        apiRequest.getDetailMovie(id,API_KEY).enqueue(object : Callback<MovieModel> {
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful) {
                    _responseDetailMovie.postValue(response.body())

                    if (! ResourceEspress.getResourceEspressid().isIdleNow) {
                        ResourceEspress.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                Log.e("getDetailMovie: ", t.message.toString())
            }

        })

        return _responseDetailMovie
    }




    override fun getDetailTV(id : Int) : LiveData<TVShowModel> {
        ResourceEspress.increment()
        apiRequest.getDetailTV(id, API_KEY).enqueue(object : Callback<TVShowModel> {
            override fun onResponse(call: Call<TVShowModel>, response: Response<TVShowModel>) {
                if (response.isSuccessful) {
                    _responseDetailTV.postValue(response.body())

                    if (! ResourceEspress.getResourceEspressid().isIdleNow) {
                        ResourceEspress.decrement()
                    }
                }
            }

            override fun onFailure(call: Call<TVShowModel>, t: Throwable) {
                Log.e("getDetailTV: ", t.message.toString())
            }

        })

        return _responseDetailTV
    }

    override fun getAllMovieFromDatabase(sort: String): DataSource.Factory<Int, MovieModel> {
        val query = Sort.getSortedQuery(Sort.TYPE_MOVIE, sort)
        return appDAO.getAllMovieFromDatabase(query)
    }

    override fun getALlTVShowFromDatabase(sort: String): DataSource.Factory<Int, TVShowModel> {
        val query = Sort.getSortedQuery(Sort.TYPE_TVSHOW, sort)
        return appDAO.getAllTVShowFromDatabase(query)
    }
    override fun findMovieFromDatabase(id: Int): LiveData<MovieModel>? {
        _findMovieDatabase = appDAO.findMovieFromDatabase(id)
        return _findMovieDatabase
    }

    override fun findTVShowFromDatabase(id: Int): LiveData<TVShowModel>? {
        _findTVShowDatabase = appDAO.findTVShowFromDatabase(id)
        return _findTVShowDatabase
    }

    override suspend fun deleteMovie(movie: MovieModel) {
        appDAO.deleteMovie(movie)
    }

    override suspend fun deleteTVShow(tvShow: TVShowModel) {
        appDAO.deleteTVShow(tvShow)
    }

    override suspend fun insert(movie: MovieModel) {
        appDAO.insert(movie)
    }

    override suspend fun insert(tvShow: TVShowModel) {
        appDAO.insert(tvShow)
    }
}