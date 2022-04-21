package com.senex.moviecollection.data.mapper

import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.domain.model.Movie

fun MovieListResponse?.transform() =
    this?.items?.map { it.transform() } ?: emptyList()

fun List<Movie>.transform() =
    MovieListResponse(map { it.transform() })