package com.example.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object Sort {
    const val TITLE = "Title"
    const val TOP_RATING = "Top_Rating"
    const val RANDOM = "Random"
    const val TYPE_MOVIE = "Type_Movie"
    const val TYPE_TVSHOW = "Type_TVShow"

    fun getSortedQuery (type: String, filter: String): SimpleSQLiteQuery {
        val simpleQuery: StringBuilder = StringBuilder()
        when (type) {
            TYPE_MOVIE -> simpleQuery.append("SELECT * FROM movie_favorite ")
            TYPE_TVSHOW -> simpleQuery.append("SELECT * FROM tvshow_favorite ")
        }

        when (filter) {
            TITLE -> simpleQuery.append("ORDER BY title ASC")
            TOP_RATING -> simpleQuery.append("ORDER BY rating DESC")
            RANDOM -> simpleQuery.append("ORDER BY RANDOM()")
        }

        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}