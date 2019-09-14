package ch.markwalther.starwars.character

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import org.junit.jupiter.api.Test

internal class CharacterTextGeneratorTest {

	@Test
	fun getText() {
		val entry = Model.CharacterList.Entry(0, "JUnit", false)

		val text = CharacterTextGenerator().getText(entry)

		assertThat(text).isEqualTo("JUnit")
	}

}