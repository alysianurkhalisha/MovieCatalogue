package com.example.moviecatalogue.tvshow

import com.google.gson.annotations.SerializedName

data class TvshowPopular (
    @SerializedName("results")
    val hasil: ArrayList<TVShowModel>
    )
