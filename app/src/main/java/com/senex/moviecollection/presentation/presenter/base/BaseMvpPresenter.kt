package com.senex.moviecollection.presentation.presenter.base

import androidx.annotation.CallSuper
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

open class BaseMvpPresenter<T : MvpView> : MvpPresenter<T>() {
    protected val lifecycleCoroutineScope = MainScope()

    @CallSuper
    override fun onDestroy() {
        lifecycleCoroutineScope.cancel()
    }
}