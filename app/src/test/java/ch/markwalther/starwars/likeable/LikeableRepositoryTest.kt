package ch.markwalther.starwars.likeable

import android.content.SharedPreferences
import assertk.assertThat
import assertk.assertions.isTrue
import io.mockk.*
import org.junit.jupiter.api.Test

internal class LikeableRepositoryTest {

	@Test
	fun isLiked() {
		val sharedPrefs = mockk<SharedPreferences>()
		every { sharedPrefs.getBoolean("1", false) } returns true

		val isLiked = LikeableRepository(sharedPrefs).isLiked(1)

		assertThat(isLiked).isTrue()
		verify(exactly = 1) { sharedPrefs.getBoolean("1", false) }
		confirmVerified(sharedPrefs)
	}

	@Test
	fun storeLike() {
		val editor = mockk<SharedPreferences.Editor>()
		every { editor.putBoolean("1", true) } returns editor
		every { editor.apply() } just Runs
		val sharedPrefs = mockk<SharedPreferences>()
		every { sharedPrefs.edit() } returns editor

		LikeableRepository(sharedPrefs).storeLike(1, true)

		verify(exactly = 1) { sharedPrefs.edit() }
		verify(exactly = 1) { editor.putBoolean("1", true) }
		verify(exactly = 1) { editor.apply() }
		confirmVerified(editor)
	}


}