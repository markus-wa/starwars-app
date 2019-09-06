package ch.markwalther.starwars.movie

import assertk.assertThat
import assertk.assertions.isSameAs
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class MovieRepositoryTest {

	@Test
	fun all() {
		val expected = Model.MovieList(listOf(Model.MovieList.Entry(1, "JUnit", true)))
		val service = mockk<MovieService>()
		coEvery { service.all() } returns expected

		val result = runBlocking { MovieRepository(service).all() }

		assertThat(result).isSameAs(expected)
	}

	@Test
	fun storeLike() {
		MovieRepository(mockk()).storeLike(1, true)

		// TODO: implement
	}

}