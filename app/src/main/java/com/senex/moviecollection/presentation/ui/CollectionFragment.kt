package com.senex.moviecollection.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.senex.moviecollection.data.api.MovieListApiService
import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.databinding.FragmentCollectionBinding
import com.senex.moviecollection.domain.util.log
import com.senex.moviecollection.presentation.common.inflateBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding
        get() = _binding!!

    @Inject
    lateinit var serv: MovieListApiService

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
        serv.getTopMovies().enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>,
            ) {
                call.request().toString().log()
                response.body().toString().log()
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}