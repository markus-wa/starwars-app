package ch.markwalther.starwars.character

import androidx.lifecycle.Observer
import ch.markwalther.starwars.InstantTaskExecutorExtension
import ch.markwalther.starwars.api.Model
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
@Disabled("flakey")
internal class CharacterViewModelTest {

	@Test
	fun getAll() {
		Dispatchers.setMain(newSingleThreadContext("JUnit"))
		val repo = mockk<CharacterRepository>()
		val expected = Model.CharacterList(listOf(Model.CharacterList.Entry(1, "JUnit", true)))
		coEvery { repo.all() } returns expected

		val observer = mockk<Observer<Model.CharacterList>>()
		every { observer.onChanged(any()) } just Runs
		CharacterViewModel(repo).all.observeForever(observer)

		verify(exactly = 1) { observer.onChanged(expected) }
		confirmVerified(observer)
	}

}