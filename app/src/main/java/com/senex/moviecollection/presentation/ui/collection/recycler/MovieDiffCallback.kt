package com.senex.moviecollection.presentation.ui.collection.recycler

import androidx.recyclerview.widget.DiffUtil
import com.senex.moviecollection.domain.model.Movie

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie,
    ) = oldItem == newItem
}