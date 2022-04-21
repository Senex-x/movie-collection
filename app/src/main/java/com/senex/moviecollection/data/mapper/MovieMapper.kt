package com.senex.moviecollection.data.mapper

import com.senex.moviecollection.data.entity.MovieEntity
import com.senex.moviecollection.domain.model.Movie

fun MovieEntity.transform() = Movie(
    crew,
    fullTitle,
    id,
    imDbRating,
    imDbRatingCount,
    image,
    rank,
    title,
    year,
)

fun Movie.transform() = MovieEntity(
    crew,
    fullTitle,
    id,
    imDbRating,
    imDbRatingCount,
    image,
    rank,
    title,
    year,
)