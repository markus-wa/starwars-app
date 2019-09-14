package ch.markwalther.starwars.character

import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter

class CharacterTextGenerator : LikeableListAdapter.TextGenerator<Model.CharacterList.Entry>() {

	override fun getText(likeable: Model.CharacterList.Entry) = likeable.name

}
