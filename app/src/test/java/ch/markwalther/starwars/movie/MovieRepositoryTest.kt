package ch.markwalther.starwars.movie

import android.content.SharedPreferences
import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService
import io.mockk.*
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

		val sharedPrefs = mockk<SharedPreferences>()
		every { sharedPrefs.getBoolean("1", false) } returns true
		every { sharedPrefs.getBoolean("2", false) } returns false

		val result = runBlocking { MovieRepository(service, sharedPrefs).all() }

		val expected = Model.MovieList(
			listOf(
				Model.MovieList.Entry(1, "JUnit", true),
				Model.MovieList.Entry(2, "JUnit2", false)
			)
		)
		assertThat(result).isEqualTo(expected)
	}

	@Test
	fun storeLike() {
		val editor = mockk<SharedPreferences.Editor>()
		every { editor.putBoolean("1", true) } returns editor
		every { editor.apply() } just Runs
		val sharedPrefs = mockk<SharedPreferences>()
		every { sharedPrefs.edit() } returns editor

		MovieRepository(mockk(), sharedPrefs).storeLike(1, true)

		verify(exactly = 1) { sharedPrefs.edit() }
		verify(exactly = 1) { editor.putBoolean("1", true) }
		verify(exactly = 1) { editor.apply() }
		confirmVerified(editor)
	}

}