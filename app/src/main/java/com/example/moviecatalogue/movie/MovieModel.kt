package com.example.moviecatalogue.movie

import android.os.Parcelable
import androidx.room.Entity
import com.example.moviecatalogue.GenreModul
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "movie_favorite", primaryKeys = ["id"])
data class MovieModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    var pict: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("vote_average")
    var rating: Float? = null,
    @SerializedName("release_date")
    var release: String? = null,
    @SerializedName("genres")
    val genre: @RawValue List<GenreModul>?=null,
    @SerializedName("overview")
    var description: String? = null
) : Parcelable