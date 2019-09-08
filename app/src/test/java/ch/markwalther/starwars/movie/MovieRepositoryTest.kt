package ch.markwalther.starwars.movie

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class MovieRepositoryTest {

	@Test
	fun all() {
		val list = Model.MovieList(
			listOf(
				Model.MovieList.Entry(0, "JUnit", false),
				Model.MovieList.Entry(0, "JUnit2", false)
			)
		)
		val service = mockk<MovieService>()
		coEvery { service.all() } returns list

		val result = runBlocking { MovieRepository(service).all() }

		val expected = Model.MovieList(
			listOf(
				Model.MovieList.Entry(1, "JUnit", false),
				Model.MovieList.Entry(2, "JUnit2", false)
			)
		)
		assertThat(result).isEqualTo(expected)
	}

}