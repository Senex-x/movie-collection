package com.senex.moviecollection.presentation.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.senex.moviecollection.databinding.FragmentCollectionBinding
import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.util.toast
import com.senex.moviecollection.presentation.common.inflateBinding
import com.senex.moviecollection.presentation.presenter.MoviesPresenter
import com.senex.moviecollection.presentation.ui.collection.recycler.CollectionRecyclerAdapter
import com.senex.moviecollection.presentation.view.MoviesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CollectionFragment : MvpAppCompatFragment(), MoviesView {
    private var _binding: FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

    @InjectPresenter // Main Moxy presenter
    lateinit var moviesPresenter: MoviesPresenter
    @Inject // Hilt presenter, do not use
    lateinit var moviesPresenterInjected: MoviesPresenter
    @ProvidePresenter
    fun providePresenter() = moviesPresenterInjected

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflateBinding(FragmentCollectionBinding::inflate, inflater, container) {
        _binding = it
    }

    private val onItemClickListener: (itemId: String) -> Unit = {
        requireContext().toast("Click!")
    }
    private val adapter = CollectionRecyclerAdapter(onItemClickListener)

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        collectionRecycler.layoutManager = LinearLayoutManager(requireContext())
        collectionRecycler.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            moviesPresenter.refresh()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayMovies(movies: List<Movie>) {
        binding.loadingFailedText.visibility = View.GONE
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(movies)
        }
    }

    override fun onStartMoviesLoading() {
        binding.moviesProgressBar.visibility = View.VISIBLE
    }

    override fun onFinishMoviesLoading() {
        binding.moviesProgressBar.visibility = View.GONE
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onMoviesLoadingFail() {
        binding.loadingFailedText.visibility = View.VISIBLE
        requireContext().toast("Loading failed!")
    }
}