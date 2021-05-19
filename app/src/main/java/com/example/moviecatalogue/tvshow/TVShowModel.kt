package com.example.moviecatalogue.tvshow

import android.os.Parcelable
import androidx.room.Entity
import com.example.moviecatalogue.GenreModul
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "tvshow_favorite", primaryKeys = ["id"])
data class TVShowModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    var pict: String? = null,
    @SerializedName("name")
    var title: String? = null,
    @SerializedName("vote_average")
    var rating: Float? = null,
    @SerializedName("first_air_date")
    var release: String? = null,
    @SerializedName("number_of_episodes")
    var episodes: Int? = null,
    @SerializedName("genres")
    val genre: @RawValue List<GenreModul>?=null,
    @SerializedName("overview")
    var description: String? = null
) : Parcelable