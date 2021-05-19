package com.example.moviecatalogue.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviecatalogue.Converter
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import dagger.hilt.android.qualifiers.ApplicationContext

@Database(entities = [MovieModel::class, TVShowModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDB: RoomDatabase() {
    abstract fun appDAO(): DAO

    companion object{
        @Volatile
        private var INSTANCE:com.example.moviecatalogue.repository.AppDB? = null

        fun getDatabase(context: Context): com.example.moviecatalogue.repository.AppDB? {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.moviecatalogue.repository.AppDB::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}