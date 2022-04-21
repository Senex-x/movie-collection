package com.senex.moviecollection.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.senex.moviecollection.databinding.FragmentCollectionBinding
import com.senex.mtgcollection.presentation.common.inflateBinding

class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

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
    ) = with(binding) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}