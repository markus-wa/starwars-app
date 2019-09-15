package ch.markwalther.starwars.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableViewModel
import kotlinx.coroutines.Dispatchers

class MovieViewModel(
	private val repository: MovieRepository
) : ViewModel(), LikeableViewModel<Model.MovieList.Entry> {

	override var displayLikedOnly = false

	override val all = liveData(Dispatchers.IO) {
		emit(repository.all().results)
	}

}