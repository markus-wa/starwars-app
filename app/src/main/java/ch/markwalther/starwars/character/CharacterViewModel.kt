package ch.markwalther.starwars.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(
	private val repository: CharacterRepository
) : ViewModel() {

	val all = liveData(Dispatchers.IO) {
		emit(repository.all())
	}

}