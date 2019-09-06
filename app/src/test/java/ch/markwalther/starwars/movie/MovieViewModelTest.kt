package ch.markwalther.starwars.movie

import androidx.lifecycle.Observer
import ch.markwalther.starwars.InstantTaskExecutorExtension
import ch.markwalther.starwars.api.Model
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class)
internal class MovieViewModelTest {

	@Test
	fun getAll() {
		Dispatchers.setMain(newSingleThreadContext("JUnit"))
		val repo = mockk<MovieRepository>()
		val expected = Model.MovieList(listOf(Model.MovieList.Entry(1, "JUnit", true)))
		coEvery { repo.all() } returns expected

		val observer = mockk<Observer<Model.MovieList>>()
		every { observer.onChanged(any()) } just Runs
		MovieViewModel(repo).all.observeForever(observer)

		verify(exactly = 1) { observer.onChanged(expected) }
	}

}