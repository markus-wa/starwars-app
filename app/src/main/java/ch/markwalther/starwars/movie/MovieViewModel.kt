package ch.markwalther.starwars.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {
    private val repository: MovieRepository = MovieRepository()

    val all = liveData(Dispatchers.IO) {
        emit(repository.all())
    }

}