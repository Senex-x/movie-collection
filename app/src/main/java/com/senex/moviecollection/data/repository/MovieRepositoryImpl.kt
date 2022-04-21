package com.senex.moviecollection.data.repository

import com.senex.moviecollection.data.api.MovieListApiService
import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.data.mapper.transform
import com.senex.moviecollection.domain.repository.MovieRepository
import com.senex.moviecollection.domain.util.log
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MovieRepositoryImpl @Inject constructor(
    private val movieListApiService: MovieListApiService,
) : MovieRepository {

    override suspend fun getTop250() = suspendCoroutine<MovieListResponse?> {
        movieListApiService.getTop250Movies().subscribe(it::resume) { throwable ->
            throwable.toString().log()
            it.resume(null)
        }
    }.transform()
}

