package ch.markwalther.starwars.movie

import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService
import ch.markwalther.starwars.likeable.LikeableRepository

class MovieRepository(
	private val service: MovieService,
	private val likeableRepo: LikeableRepository
) {

	suspend fun all(): Model.MovieList {
		val apiRes = service.all()
		val indexedResult = apiRes.results.mapIndexed { i, it ->
			// results are ordered by release date and the index (starting at 1) represents the ID
			val id = i + 1
			val liked = likeableRepo.isLiked(id)
			it.copy(id = id, liked = liked)
		}
		return apiRes.copy(results = indexedResult)
	}

}