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

    @InjectPresenter // Moxy presenter
    lateinit var moviesPresenterMoxy: MoviesPresenter

    @Inject // Hilt presenter
    lateinit var moviesPresenter: MoviesPresenter

    @ProvidePresenter
    fun providePresenter() = moviesPresenter

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun displayMovies(movies: List<Movie>) {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(movies)
        }
    }
}