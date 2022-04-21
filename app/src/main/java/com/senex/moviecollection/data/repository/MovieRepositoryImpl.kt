package com.senex.moviecollection.data.repository

import com.senex.moviecollection.data.api.MovieListApiService
import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.data.mapper.transform
import com.senex.moviecollection.domain.repository.MovieRepository
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class MovieRepositoryImpl @Inject constructor(
    private val movieListApiService: MovieListApiService,
) : MovieRepository {

    override suspend fun getTop250() = suspendCoroutine<MovieListResponse?> {
        movieListApiService.getTop250Movies().enqueue(continuationCallback(it))
    }.transform()
}