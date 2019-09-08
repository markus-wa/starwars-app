package ch.markwalther.starwars.api

object Model {

	interface Likeable {
		val id: Int
		var liked: Boolean
	}

	data class MovieList(
		val results: List<Entry>
	) {
		data class Entry(
			override val id: Int,
			val title: String,
			override var liked: Boolean
		) : Likeable
	}

	data class Movie(
		val title: String
	)

	data class CharacterList(
		val results: List<Entry>
	) {
		data class Entry(
			override val id: Int,
			val name: String,
			override var liked: Boolean
		) : Likeable
	}

}


