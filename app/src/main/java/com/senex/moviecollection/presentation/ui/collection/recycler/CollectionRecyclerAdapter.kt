package com.senex.moviecollection.presentation.ui.collection.recycler

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.senex.moviecollection.R
import com.senex.moviecollection.databinding.ListItemMovieBinding
import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.presentation.common.bindListItemView
import com.senex.moviecollection.presentation.ui.collection.recycler.CollectionRecyclerAdapter.ViewHolder

class CollectionRecyclerAdapter(
    private val onItemClickListener: (itemId: String) -> Unit,
) : ListAdapter<Movie, ViewHolder>(
    MovieDiffCallback
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        bindListItemView(
            ListItemMovieBinding::bind,
            parent,
            R.layout.list_item_movie
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(
        private val binding: ListItemMovieBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Movie): Unit = with(binding) {
            root.setOnClickListener {
                onItemClickListener(item.id)
            }

            name.text = item.title
        }
    }
}