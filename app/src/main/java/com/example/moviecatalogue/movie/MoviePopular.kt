package com.example.moviecatalogue.movie

import com.google.gson.annotations.SerializedName

data class MoviePopular(
@SerializedName("results")
val hasil: ArrayList<MovieModel>
)
