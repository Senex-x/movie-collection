package com.senex.moviecollection.presentation.presenter

import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.view.`MoviesView$$State`
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
internal class MoviesPresenterTest {
    private val mainThreadSurrogate = newSingleThreadContext("Main")

    lateinit var presenter: MoviesPresenter
    private val viewState = mockk<`MoviesView$$State`>(relaxed = true)
    private val getMovies = mockk<GetMovies>(relaxed = true)
    private val movies = buildList<Movie> {
        for (i in 0 until 250) {
            add(mockk())
        }
    }

    @BeforeEach
    fun beforeEach() {
        Dispatchers.setMain(mainThreadSurrogate)

        coEvery { getMovies.invoke() } returns movies
        presenter = MoviesPresenter(getMovies)
        presenter.setViewState(viewState)
    }

    @Test
    fun `Should perform refresh sequence`() {
        presenter.refresh()

        verifyOrder {
            viewState.onStartMoviesLoading()
            viewState.displayMovies(movies)
            viewState.onFinishMoviesLoading()
        }
    }

    @Test
    fun `Should fail refresh sequence`() {
        coEvery { getMovies.invoke() } returns emptyList()

        presenter.refresh()

        verifyOrder {
            viewState.onStartMoviesLoading()
            viewState.onMoviesLoadingFail()
            viewState.onFinishMoviesLoading()
        }
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }
}