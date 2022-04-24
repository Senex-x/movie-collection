package com.senex.moviecollection.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.senex.moviecollection.databinding.FragmentCollectionBinding
import com.senex.moviecollection.presentation.common.inflateBinding
import com.senex.moviecollection.presentation.mvp.presenter.BruhPresenter
import com.senex.moviecollection.presentation.mvp.view.BruhView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CollectionFragment : MvpAppCompatFragment(), BruhView {
    private var _binding: FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

    @InjectPresenter
    lateinit var presenter: BruhPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ) = inflateBinding(FragmentCollectionBinding::inflate, inflater, container) {
        _binding = it
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ): Unit = with(binding) {
        presenter.postMessage("BRUH???????????")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showMessage(message: String) {
        binding.textView.text = message
    }
}