package com.senex.moviecollection.domain.usecase

import com.senex.moviecollection.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovies @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        pageSize: Int = 250,
        pageIndex: Int = 0,
    ) = movieRepository.getTop250().takeIf { it.isNotEmpty() }?.subList(
        pageSize * pageIndex,
        pageSize * (pageIndex + 1)
    ) ?: emptyList()
}