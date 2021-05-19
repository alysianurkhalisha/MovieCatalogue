package com.example.moviecatalogue.utils

import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.movie.MoviePopular
import com.example.moviecatalogue.tvshow.TVShowModel
import com.example.moviecatalogue.tvshow.TvshowPopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestApi {
    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviePopular>

    @GET("/3/movie/{movie_id}")
    fun getDetailMovie(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<MovieModel>

    @GET("/3/tv/popular")
    fun getPopularTVs(@Query("api_key") apiKey: String): Call<TvshowPopular>

    @GET("/3/tv/{tv_id}")
    fun getDetailTV(@Path("tv_id") id: Int, @Query("api_key") apiKey: String): Call<TVShowModel>

}
