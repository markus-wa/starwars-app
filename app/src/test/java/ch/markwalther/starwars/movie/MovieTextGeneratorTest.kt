package ch.markwalther.starwars.movie

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import org.junit.jupiter.api.Test

internal class MovieTextGeneratorTest {

	@Test
	fun getText() {
		val entry = Model.MovieList.Entry(0, "JUnit", false)

		val text = MovieTextGenerator().getText(entry)

		assertThat(text).isEqualTo("JUnit")
	}

}