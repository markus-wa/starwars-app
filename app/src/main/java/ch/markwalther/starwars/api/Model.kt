package ch.markwalther.starwars.api

object Model {

	interface Likeable {
		var liked: Boolean
	}

	data class MovieList(
		val results: List<Entry>
	) {
		data class Entry(
			val id: Int,
			val title: String,
			override var liked: Boolean
		) : Likeable
	}

	data class Movie(
		val title: String
	)

}


