package com.senex.moviecollection.domain.usecase

import com.senex.moviecollection.domain.model.Movie
import com.senex.moviecollection.domain.repository.MovieRepository
import com.senex.moviecollection.util.MainThreadExtension
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

internal class GetMoviesTest {
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
    @ExperimentalCoroutinesApi
    fun `Should return unchanged movies`() = runTest {
        val movie = getMovies().first()

        assertEquals(movie.id, TEST_ID)
        assertEquals(movie.fullTitle, TEST_TITLE)
    }
}