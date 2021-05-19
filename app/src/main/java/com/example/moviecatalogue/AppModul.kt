package com.example.moviecatalogue

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.moviecatalogue.repository.AppDB
import com.example.moviecatalogue.repository.AppRepository
import com.example.moviecatalogue.repository.DAO
import com.example.moviecatalogue.utils.RequestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModul {
    @Provides
    fun provideDAO(appDatabase: AppDB): DAO {
        return appDatabase.appDAO()
    }

    @Provides
    fun provideRepository(appDAO: DAO, apiRequest: RequestApi): AppRepository {
        return AppRepository(appDAO, apiRequest)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDB {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            "movie_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): RequestApi {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.themoviedb.org/")
            .client(client)
            .build()
        return retrofit.create(RequestApi::class.java)
    }

}