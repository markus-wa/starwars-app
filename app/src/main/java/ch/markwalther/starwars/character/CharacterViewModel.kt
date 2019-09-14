package ch.markwalther.starwars.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableViewModel
import kotlinx.coroutines.Dispatchers

class CharacterViewModel(
	private val repository: CharacterRepository
) : ViewModel(), LikeableViewModel<Model.CharacterList.Entry> {

	override val all = liveData(Dispatchers.IO) {
		emit(repository.all().results)
	}

}