package com.senex.moviecollection.presentation.ui.collection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.senex.moviecollection.databinding.FragmentCollectionBinding
import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.usecase.GetMovies
import com.senex.moviecollection.presentation.common.inflateBinding
import com.senex.moviecollection.presentation.presenter.BruhPresenter
import com.senex.moviecollection.presentation.presenter.MoviesPresenter
import com.senex.moviecollection.presentation.ui.collection.recycler.CollectionRecyclerAdapter
import com.senex.moviecollection.presentation.view.BruhView
import com.senex.moviecollection.presentation.view.MoviesView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CollectionFragment : MvpAppCompatFragment(), BruhView, MoviesView {
    private var _binding: FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

    @InjectPresenter
    lateinit var presenter: BruhPresenter

    @InjectPresenter
    lateinit var moviesPresenter: MoviesPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflateBinding(FragmentCollectionBinding::inflate, inflater, container) {
        _binding = it
    }

    private val adapter = CollectionRecyclerAdapter({ })

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        presenter.postMessage("BRUH")

        moviesPresenter.init(requireContext()).getTopMovies()

        collectionRecycler.layoutManager = LinearLayoutManager(requireContext())
        collectionRecycler.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMessage(message: String) {
        binding.textView.text = message
    }

    override fun displayMovies(movies: List<Movie>) {
        viewLifecycleOwner.lifecycleScope.launch {
            adapter.submitList(movies)
        }
    }
}