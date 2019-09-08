package ch.markwalther.starwars.movie

import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.likeable.LikeableRepository

class MovieListAdapter(
	repository: LikeableRepository,
	data: List<Model.MovieList.Entry>
) : LikeableListAdapter<Model.MovieList.Entry>(repository, data) {

	override fun getText(likeable: Model.MovieList.Entry) = likeable.title

}
