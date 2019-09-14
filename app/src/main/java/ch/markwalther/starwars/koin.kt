package ch.markwalther.starwars

import android.app.Application
import android.content.Context
import ch.markwalther.starwars.api.SWApiClient
import ch.markwalther.starwars.character.CharacterRepository
import ch.markwalther.starwars.character.CharacterTextGenerator
import ch.markwalther.starwars.character.CharacterViewModel
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.likeable.LikeableRepository
import ch.markwalther.starwars.movie.MovieRepository
import ch.markwalther.starwars.movie.MovieTextGenerator
import ch.markwalther.starwars.movie.MovieViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


object Bean {
	const val LIKEABLE_REPO = "LikeableRepo_"

	object LikeableRepo {
		const val MOVIE = LIKEABLE_REPO + Type.MOVIE
		const val CHARACTERS = LIKEABLE_REPO + Type.CHARACTER
	}

	const val LIKEABLE_LIST_ADAPTER = "LikeableListAdapter_"

	object LikeableListAdapter {
		const val MOVIE = LIKEABLE_LIST_ADAPTER + Type.MOVIE
		const val CHARACTERS = LIKEABLE_LIST_ADAPTER + Type.CHARACTER
	}

	const val VIEW_MODEL = "ViewModel_"

	object ViewModel {
		const val MOVIE = VIEW_MODEL + Type.MOVIE
		const val CHARACTERS = VIEW_MODEL + Type.CHARACTER
	}

	object Type {
		enum class Enum(val type: String) {
			MOVIE(Bean.Type.MOVIE),
			CHARACTER(Bean.Type.CHARACTER)
		}

		const val MOVIE = "movies"
		const val CHARACTER = "characters"
	}
}

// dependency injection config
val koinStarWarsModule = module {
	val swApiClient = SWApiClient("https://swapi.co/api/")
	single { swApiClient.movieService }
	single { swApiClient.characterService }

	single { MovieRepository(get(), get(named(Bean.LikeableRepo.MOVIE))) }
	single { CharacterRepository(get(), get(named(Bean.LikeableRepo.CHARACTERS))) }

	single(named(Bean.LikeableRepo.MOVIE)) { likeableRepo(androidApplication(), "movies") }
	single(named(Bean.LikeableRepo.CHARACTERS)) { likeableRepo(androidApplication(), "characters") }

	single { MovieTextGenerator() }
	single { CharacterTextGenerator() }

	factory(named(Bean.LikeableListAdapter.MOVIE)) {
		LikeableListAdapter(
			get(named(Bean.LikeableRepo.MOVIE)),
			get<MovieTextGenerator>()
		)
	}

	factory(named(Bean.LikeableListAdapter.CHARACTERS)) {
		LikeableListAdapter(
			get(named(Bean.LikeableRepo.CHARACTERS)),
			get<CharacterTextGenerator>()
		)
	}

	viewModel(named(Bean.ViewModel.MOVIE)) { MovieViewModel(get()) }
	viewModel(named(Bean.ViewModel.CHARACTERS)) { CharacterViewModel(get()) }
}

fun likeableRepo(androidApplication: Application, name: String): LikeableRepository {
	val sharedPrefs = androidApplication.getSharedPreferences(name, Context.MODE_PRIVATE)
	return LikeableRepository(sharedPrefs)
}