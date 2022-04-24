package com.senex.moviecollection.presentation.presenter

import android.content.Context
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.view.MoviesView
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@InjectViewState
class MoviesPresenter : MvpPresenter<MoviesView>() {
    private val lifecycleCoroutineScope = CoroutineScope(Dispatchers.Default)

    fun init(context: Context): InitializedPresenter {
        val appContext = context.applicationContext
        val getMovies = EntryPointAccessors.fromApplication(appContext, MoviesPresenterEntryPoint::class.java).getMovies()
        return InitializedPresenter(getMovies)
    }

    inner class InitializedPresenter(private val getMovies: GetMovies) {
        fun getTopMovies() {
            lifecycleCoroutineScope.launch {
                viewState.displayMovies(getMovies())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleCoroutineScope.cancel()
    }

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MoviesPresenterEntryPoint {
        fun getMovies(): GetMovies
    }
}