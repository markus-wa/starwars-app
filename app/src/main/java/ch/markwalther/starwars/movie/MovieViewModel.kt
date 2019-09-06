package ch.markwalther.starwars.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MovieViewModel(
	private val repository: MovieRepository
) : ViewModel() {

	val all = liveData(Dispatchers.IO) {
		emit(repository.all())
	}

}