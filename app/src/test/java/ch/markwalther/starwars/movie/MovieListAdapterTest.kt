package ch.markwalther.starwars.movie

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class MovieListAdapterTest {

	@Test
	fun getText() {
		val entry = Model.MovieList.Entry(0, "JUnit", false)

		val text = MovieListAdapter(mockk(), mockk()).getText(entry)

		assertThat(text).isEqualTo("JUnit")
	}

}