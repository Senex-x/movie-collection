package com.senex.moviecollection.presentation.view

import com.arellomobile.mvp.MvpView

interface BruhView: MvpView {
    fun showMessage(message: String)
}