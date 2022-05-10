package com.senex.moviecollection.domain.usecase

import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.repository.MovieRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class GetMoviesTest {
    companion object {
        private const val TEST_ID = "Test id"
        private const val TEST_TITLE = "Test Title"
        private val movies = mutableListOf<Movie>(
            mockk {
                every { id } returns TEST_ID
                every { fullTitle } returns TEST_TITLE
            }
        )

        init {
            for (i in 1 until 250) {
                movies.add(mockk())
            }
        }

        private val repository = mockk<MovieRepository>()
        private lateinit var getMovies: GetMovies

        @JvmStatic
        @BeforeAll
        fun setup() {
            coEvery { repository.getTop250() } returns movies
            getMovies = GetMovies(repository)
        }
    }

    @Test
    fun `Should return unchanged movies`() {
        runBlocking {
            val movie = getMovies().first()

            assertEquals(movie.id, TEST_ID)
            assertEquals(movie.fullTitle, TEST_TITLE)
        }
    }
}