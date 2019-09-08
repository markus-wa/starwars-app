package ch.markwalther.starwars

import android.app.Application
import android.content.Context
import ch.markwalther.starwars.api.SWApiClient
import ch.markwalther.starwars.likeable.LikeableRepository
import ch.markwalther.starwars.movie.MovieRepository
import ch.markwalther.starwars.movie.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


const val LIKEABLE_BEAN_MOVIES = "movies"
const val LIKEABLE_BEAN_CHARACTERS = "characters"

// dependency injection config
val koinStarWarsModule = module {
	val swApiClient = SWApiClient("https://swapi.co/api/")
	single { swApiClient.movieService }
	single { MovieRepository(get()) }
	single(named(LIKEABLE_BEAN_MOVIES)) { likeableRepo(androidApplication(), "movies") }
	single(named(LIKEABLE_BEAN_CHARACTERS)) { likeableRepo(androidApplication(), "characters") }
	viewModel { MovieViewModel(get()) }
}

fun likeableRepo(androidApplication: Application, name: String): LikeableRepository {
	val sharedPrefs = androidApplication.getSharedPreferences(name, Context.MODE_PRIVATE)
	return LikeableRepository(sharedPrefs)
}