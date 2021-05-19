package com.example.moviecatalogue

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class AppRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataFake::class.java)
    private val repository = RepositoryFake(remote)

    private val moviesResponses = DataTest.generateDataMovies()
    private val movieContent = moviesResponses[0]
    private val tvshowResponses = DataTest.generateDataTVShow()
    private val tvshowContent = tvshowResponses[0]

    @Test
    fun getPopularMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataFake.LoadMoviesCallback)
                .onAllMoviesReceived(moviesResponses)
            null
        }.`when`(remote).getPopularMovies(any())
        val moviesEntities = LiveDataTest.getValue(repository.getPopularMovies())
        verify(remote).getPopularMovies(any())
        Assert.assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataFake.LoadMovieContentCallback)
                .onContentReceived(movieContent)
            null
        }.`when`(remote).getMovieContent(eq(movieContent.id), any())

        val movieEntitiesContent = LiveDataTest.getValue(repository.getDetailMovie(movieContent.id))

        verify(remote)
            .getMovieContent(eq(movieContent.id), any())

        Assert.assertNotNull(movieEntitiesContent)
        assertEquals(movieContent.id, movieEntitiesContent.id)
    }

    @Test
    fun getPopularTVs() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataFake.LoadTVShowCallback)
                .onAllTVShowReceived(tvshowResponses)
            null
        }.`when`(remote).getPopularTVShow(any())
        val tvshowEntities = LiveDataTest.getValue(repository.getPopularTv())
        verify(remote).getPopularTVShow(any())
        Assert.assertNotNull(tvshowEntities)
        assertEquals(tvshowResponses.size.toLong(), tvshowEntities.size.toLong())
    }

    @Test
    fun getDetailTV() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataFake.LoadTVContentCallback)
                .onContentReceived(tvshowContent)
            null
        }.`when`(remote).getTVContent(eq(tvshowContent.id), any())

        val tvshowEntitiesContent = LiveDataTest.getValue(repository.getDetailTV(tvshowContent.id))

        verify(remote)
            .getTVContent(eq(tvshowContent.id), any())

        Assert.assertNotNull(tvshowEntitiesContent)
        assertEquals(tvshowContent.id, tvshowEntitiesContent.id)
    }
}
