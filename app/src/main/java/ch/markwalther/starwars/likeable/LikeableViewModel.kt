package ch.markwalther.starwars.likeable

import androidx.lifecycle.LiveData
import ch.markwalther.starwars.api.Model

interface LikeableViewModel<T : Model.Likeable> {
	var displayLikedOnly: Boolean

	val all: LiveData<List<T>>
}
