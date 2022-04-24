package com.senex.moviecollection.presentation.view

import com.arellomobile.mvp.MvpView
import com.senex.moviecollection.domain.model.Movie

interface MoviesView: MvpView {
    fun displayMovies(movies: List<Movie>)

    fun onStartMoviesLoading()

    fun onFinishMoviesLoading()
}