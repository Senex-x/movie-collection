package com.senex.moviecollection.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.senex.moviecollection.presentation.view.BruhView

@InjectViewState
class BruhPresenter : MvpPresenter<BruhView>() {
    fun postMessage(message: String) = viewState.showMessage(message)
}