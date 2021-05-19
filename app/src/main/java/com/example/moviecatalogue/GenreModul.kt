package com.example.moviecatalogue

import com.google.gson.annotations.SerializedName

data class GenreModul (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
    )
