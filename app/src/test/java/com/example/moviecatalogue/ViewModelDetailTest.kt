package com.example.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelDetailTest {
    private lateinit var viewModel: ViewModelDetail

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: RepositoryFake

    private val idMovie = 316029
    private val idTVShow = 96102


    @Before
    fun setUp() {
        viewModel = ViewModelDetail(repository)
    }


    @Test
    fun setSelectedData() {
        viewModel.setSelectedData(idMovie)
        Assert.assertEquals(idMovie, viewModel.id)
    }

    @Test
    fun getSelectedMovie() {
        viewModel.setSelectedData(idMovie)

        val dummyMovie = DataTest.generateDataMovies()[0]
        val movies = MutableLiveData<MovieModel>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getDetailMovie(idMovie)).thenReturn(movies)
        val dataMovie = viewModel.getSelectedMovie().value
        Mockito.verify(repository).getDetailMovie(idMovie)

        Assert.assertNotNull(dataMovie)

        assertEquals(dummyMovie.id, dataMovie?.id)
        assertEquals(dummyMovie.title, dataMovie?.title)
        assertEquals(dummyMovie.pict, dataMovie?.pict)
        assertEquals(dummyMovie.rating, dataMovie?.rating)
        assertEquals(dummyMovie.release, dataMovie?.release)
        assertEquals(dummyMovie.genre, dataMovie?.genre)
        assertEquals(dummyMovie.description,dataMovie?.description)
    }

    @Test
    fun getSelectedTVShow() {
        viewModel.setSelectedData(idTVShow)

        val dummyTVShow = DataTest.generateDataTVShow()[0]
        val tvShow = MutableLiveData<TVShowModel>()
        tvShow.value = dummyTVShow

        Mockito.`when`(repository.getDetailTV(idTVShow)).thenReturn(tvShow)
        val dataTVShow = viewModel.getSelectedTVShow().value
        Mockito.verify(repository).getDetailTV(idTVShow)

        Assert.assertNotNull(dataTVShow)

        assertEquals(dummyTVShow.id, dataTVShow?.id)
        assertEquals(dummyTVShow.title, dataTVShow?.title)
        assertEquals(dummyTVShow.pict, dataTVShow?.pict)
        assertEquals(dummyTVShow.rating, dataTVShow?.rating)
        assertEquals(dummyTVShow.release, dataTVShow?.release)
        assertEquals(dummyTVShow.episodes, dataTVShow?.episodes)
        assertEquals(dummyTVShow.genre, dataTVShow?.genre)
        assertEquals(dummyTVShow.description,dataTVShow?.description)
    }

}