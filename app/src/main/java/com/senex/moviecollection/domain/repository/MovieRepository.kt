package com.senex.moviecollection.domain.repository

import com.senex.moviecollection.domain.model.Movie

interface MovieRepository {
    suspend fun getTop250(): List<Movie>
}