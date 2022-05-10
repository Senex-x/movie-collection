package com.senex.moviecollection.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.presenter.base.BaseMvpPresenter
import com.senex.moviecollection.presentation.view.MoviesView
import kotlinx.coroutines.launch
import javax.inject.Inject

@InjectViewState
class MoviesPresenter @Inject constructor(
    private val getMovies: GetMovies,
) : BaseMvpPresenter<MoviesView>() {
    init {
        loadAndDisplayMovies()
    }

    fun refresh() {
        loadAndDisplayMovies()
    }

    private fun loadAndDisplayMovies() {
        lifecycleCoroutineScope.launch {
            viewState.apply {
                onStartMoviesLoading()

                val movies = getMovies()
                if (movies.isNotEmpty())
                    displayMovies(movies)
                else
                    onMoviesLoadingFail()

                onFinishMoviesLoading()
            }
        }
    }
}