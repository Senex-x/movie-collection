package com.senex.moviecollection.presentation.presenter

import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.view.`MoviesView$$State`
import com.senex.moviecollection.util.MainThreadExtension
import com.senex.moviecollection.util.RxSchedulerExtension
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(MainThreadExtension::class, RxSchedulerExtension::class)
internal class MoviesPresenterTest {
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
}