package ch.markwalther.starwars.movie

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService
import ch.markwalther.starwars.likeable.LikeableRepository
import io.mockk.coEvery
import io.mockk.every
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

		val likeableRepo = mockk<LikeableRepository>()
		every { likeableRepo.isLiked(1) } returns true
		every { likeableRepo.isLiked(2) } returns false

		val result = runBlocking {
			MovieRepository(service, likeableRepo).all()
		}

		val expected = Model.MovieList(
			listOf(
				Model.MovieList.Entry(1, "JUnit", true),
				Model.MovieList.Entry(2, "JUnit2", false)
			)
		)
		assertThat(result).isEqualTo(expected)
	}

}