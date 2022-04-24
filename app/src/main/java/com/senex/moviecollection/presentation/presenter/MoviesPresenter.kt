package com.senex.moviecollection.presentation.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.view.MoviesView
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@InjectViewState
class MoviesPresenter @Inject constructor(
    @ApplicationContext appContext: Context,
) : BaseMvpPresenter<MoviesView>() {

    private val getMovies = EntryPointAccessors.fromApplication(
        appContext, MoviesPresenterEntryPoint::class.java
    ).getMovies()

    init {
        loadAndDisplayMovies()
    }

    fun refresh() {
        viewState.displayMovies(emptyList())
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


    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MoviesPresenterEntryPoint {
        fun getMovies(): GetMovies
    }
}