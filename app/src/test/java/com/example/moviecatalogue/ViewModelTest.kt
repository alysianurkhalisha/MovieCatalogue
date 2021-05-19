package com.example.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.movie.MovieModel
import com.example.moviecatalogue.tvshow.TVShowModel
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
class ViewModelTest {
    private lateinit var viewModel: ViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observerMovie: Observer<List<MovieModel>>
    @Mock
    private lateinit var observerTVShow: Observer<List<TVShowModel>>

    @Mock
    private lateinit var repository: RepositoryFake

    @Before
    fun setUp() {
        viewModel = ViewModel(repository)
    }

    @Test
    fun getMovies() {
        val dummyMovie = DataTest.generateDataMovies()
        val movies = MutableLiveData<List<MovieModel>>()
        movies.value = dummyMovie

        Mockito.`when`(repository.getPopularMovies()).thenReturn(movies as LiveData<ArrayList<MovieModel>>)
        val movieEntities = viewModel.getMovie().value
        Mockito.verify(repository).getPopularMovies()
        Assert.assertNotNull(movies)
        Assert.assertEquals(2, movieEntities?.size)

        viewModel.getMovie().observeForever(observerMovie)
        Mockito.verify(observerMovie).onChanged(dummyMovie)
    }

    @Test
    fun getTVShow() {

        val dummyTVShow = DataTest.generateDataTVShow()
        val tvShow = MutableLiveData<List<TVShowModel>>()
        tvShow.value = dummyTVShow

        Mockito.`when`(repository.getPopularTv()).thenReturn(tvShow as LiveData<ArrayList<TVShowModel>>)
        val tvshowEntities = viewModel.getTVShow().value
        Mockito.verify(repository).getPopularTv()
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(2, tvshowEntities?.size)

        viewModel.getTVShow().observeForever(observerTVShow)
        Mockito.verify(observerTVShow).onChanged(dummyTVShow)
    }
}