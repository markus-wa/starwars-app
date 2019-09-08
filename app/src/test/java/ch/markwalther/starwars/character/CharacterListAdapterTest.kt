package ch.markwalther.starwars.character

import assertk.assertThat
import assertk.assertions.isEqualTo
import ch.markwalther.starwars.api.Model
import io.mockk.mockk
import org.junit.jupiter.api.Test

internal class CharacterListAdapterTest {

	@Test
	fun getText() {
		val entry = Model.CharacterList.Entry(0, "JUnit", false)

		val text = CharacterListAdapter(mockk(), mockk()).getText(entry)

		assertThat(text).isEqualTo("JUnit")
	}

}