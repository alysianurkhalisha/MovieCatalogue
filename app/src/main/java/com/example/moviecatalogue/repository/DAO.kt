package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: MovieModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TVShowModel)

    @Delete
    suspend fun deleteMovie(movie: MovieModel)

    @Delete
    suspend fun deleteTVShow(tvShow: TVShowModel)

    @RawQuery(observedEntities = [MovieModel::class])
    fun getAllMovieFromDatabase(query: SimpleSQLiteQuery): DataSource.Factory<Int, MovieModel>


    @RawQuery(observedEntities = [TVShowModel::class])
    fun getAllTVShowFromDatabase(query: SimpleSQLiteQuery): DataSource.Factory<Int, TVShowModel>

    @Query("select * from movie_favorite where id = :id")
    fun findMovieFromDatabase(id: Int): LiveData<MovieModel>

    @Query("select * from tvshow_favorite where id = :id")
    fun findTVShowFromDatabase(id: Int): LiveData<TVShowModel>
}