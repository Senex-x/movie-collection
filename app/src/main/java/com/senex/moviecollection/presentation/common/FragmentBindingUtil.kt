package com.senex.moviecollection.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <reified T : ViewBinding> inflateBinding(
    bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T,
    layoutInflater: LayoutInflater,
    root: ViewGroup?,
    initializer: (T) -> Unit,
) = bindingInflater(layoutInflater, root, false).also(initializer).root