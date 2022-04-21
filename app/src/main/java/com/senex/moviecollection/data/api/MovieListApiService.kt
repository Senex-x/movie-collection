package com.senex.moviecollection.data.api

import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.presentation.di.RetrofitModule
import retrofit2.Call
import retrofit2.http.GET

interface MovieListApiService {

    @GET("Top250Movies/${RetrofitModule.API_KEY}")
    fun getTopMovies(
    ): Call<MovieListResponse>
}