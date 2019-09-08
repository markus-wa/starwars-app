package ch.markwalther.starwars.character

import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.likeable.LikeableRepository

class CharacterListAdapter(
	repository: LikeableRepository,
	data: List<Model.CharacterList.Entry>
) : LikeableListAdapter<Model.CharacterList.Entry>(repository, data) {

	override fun getText(likeable: Model.CharacterList.Entry) = likeable.name

}
