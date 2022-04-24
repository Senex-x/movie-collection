package com.senex.moviecollection.presentation.presenter

import androidx.annotation.CallSuper
import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

open class BaseMvpPresenter<T : MvpView> : MvpPresenter<T>() {
    protected val lifecycleCoroutineScope = CoroutineScope(Dispatchers.Default)

    @CallSuper
    override fun onDestroy() {
        lifecycleCoroutineScope.cancel()
    }
}