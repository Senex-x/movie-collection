package com.senex.moviecollection.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.senex.moviecollection.domain.model.Movie

interface MoviesView: MvpView {
    @StateStrategyType(SingleStateStrategy::class)
    fun displayMovies(movies: List<Movie>)

    @StateStrategyType(SingleStateStrategy::class)
    fun onStartMoviesLoading()

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun onFinishMoviesLoading()

    @StateStrategyType(SingleStateStrategy::class)
    fun onMoviesLoadingFail()
}