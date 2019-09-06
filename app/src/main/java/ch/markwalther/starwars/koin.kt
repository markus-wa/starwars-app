package ch.markwalther.starwars

import ch.markwalther.starwars.api.SWApiClient
import ch.markwalther.starwars.movie.MovieRepository
import ch.markwalther.starwars.movie.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// dependency injection config
val koinStarWarsModule = module {
	val swApiClient = SWApiClient("https://swapi.co/api/")
	single { swApiClient.movieService }
	single { MovieRepository(get()) }
	viewModel { MovieViewModel(get()) }
}