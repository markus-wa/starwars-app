package ch.markwalther.starwars.character

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.CharacterService
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CharacterRepositoryTest {

	@Test
	fun all() {
		val list = Model.CharacterList(
			listOf(
				Model.CharacterList.Entry(0, "JUnit", false),
				Model.CharacterList.Entry(0, "JUnit2", false)
			)
		)
		val service = mockk<CharacterService>()
		coEvery { service.all() } returns list

		val likeableRepo = mockk<LikeableRepository>()
		every { likeableRepo.isLiked(1) } returns true
		every { likeableRepo.isLiked(2) } returns false

		val result = runBlocking {
			CharacterRepository(service, likeableRepo).all()
		}

		val expected = Model.CharacterList(
			listOf(
				Model.CharacterList.Entry(1, "JUnit", true),
				Model.CharacterList.Entry(2, "JUnit2", false)
			)
		)
		assertThat(result).isEqualTo(expected)
	}

}