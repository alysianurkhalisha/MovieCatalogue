package com.example.moviecatalogue

import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import java.util.ArrayList

object DataTest {
        fun generateDataMovies(): List<MovieModel> {

            val movies = ArrayList<MovieModel>()

            movies.add(
                MovieModel(316029,
                    "/b9CeobiihCx1uG1tpw8hXmpi7nm.jpg",
                    "The Greatest Showman",
                    8.0f,
                    "2017-12-29",
                    genre=listOf(
                            GenreModul(
                                id = 18,
                                name = "Drama"
                            )

                    ),
                    "The story of American showman P.T. Barnum, founder of the circus that became the famous traveling Ringling Bros. and Barnum & Bailey Circus."
                )
            )

            movies.add(
                MovieModel(198663,
                    "/ode14q7WtDugFDp78fo9lCsmay9.jpg",
                    "Maze Runner",
                    7.2f,
                    "2014-18-09",
                    genre=listOf(
                        GenreModul(
                            id = 80,
                            name = "Crime"
                    ),
                         GenreModul(
                             id = 28,
                            name = "Action"
                    )
                ),
                    "Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow “runners” for a shot at escape."
                )
            )

            return movies
        }

        fun generateDataTVShow(): List<TVShowModel> {

            val tvShow = ArrayList<TVShowModel>()

            tvShow.add(
                TVShowModel(
                    96102,
                    "pCTWzOIYVM92gXc52o9mVYESAfF.jpg",
                    "Hospital Playlist",
                    8.8f,
                    "2020-03-12",
                    16,
                    genre=listOf(
                    GenreModul(
                        id = 18,
                        name = "Drama"
                    )
                ),
                    "Every day is extraordinary for five doctors and their patients inside a hospital, where birth, death and everything in between coexist."
                )
            )
            tvShow.add(
                TVShowModel(
                    86423,
                    "/lKhF0QX724VS2QqBzSZ4KJif3Ny.jpg",
                    "Locke&Key",
                    8.1f,
                    "2020-02-07",
                    10,
                    genre= listOf(
                    GenreModul(
                        id = 10765,
                        name = "Sci-Fi & Fantasy"
                ),
                    GenreModul(
                        id = 18,
                        name = "Drama"
                    )
                ),

                    "Three siblings who move into their ancestral estate after their father's gruesome murder discover their new home's magical keys, which must be used in their stand against an evil creature who wants the keys and their powers."
                )
            )

            return tvShow
        }
    }