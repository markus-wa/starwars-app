package ch.markwalther.starwars

import android.app.Application
import android.content.SharedPreferences
import ch.markwalther.starwars.api.SWApiClient
import ch.markwalther.starwars.movie.MovieRepository
import ch.markwalther.starwars.movie.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// dependency injection config
val koinStarWarsModule = module {
	val swApiClient = SWApiClient("https://swapi.co/api/")
	single { swApiClient.movieService }
	single { MovieRepository(get(), getSharedPrefs(androidApplication(), "movies")) }
	viewModel { MovieViewModel(get()) }
}

fun getSharedPrefs(androidApplication: Application, name: String): SharedPreferences {
	return androidApplication.getSharedPreferences(name, android.content.Context.MODE_PRIVATE)
}