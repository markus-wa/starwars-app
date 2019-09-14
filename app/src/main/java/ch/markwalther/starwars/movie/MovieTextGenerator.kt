package ch.markwalther.starwars.movie

import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter

class MovieTextGenerator : LikeableListAdapter.TextGenerator<Model.MovieList.Entry>() {

	override fun getText(likeable: Model.MovieList.Entry) = likeable.title

}
