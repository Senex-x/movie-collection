package com.senex.moviecollection.data.mapper

import com.senex.moviecollection.data.entity.MovieListResponse
import com.senex.moviecollection.domain.model.Movie

fun MovieListResponse.transform() =
    movies.map { it.transform() }

fun List<Movie>.transform() =
    MovieListResponse(map { it.transform() })