package ch.markwalther.starwars.movie

import ch.markwalther.starwars.api.MovieService

class MovieRepository(
	private val service: MovieService
) {

	suspend fun all() = service.all()

	fun storeLike(id: Int, checked: Boolean) {
		TODO("storeLike is not implemented")
	}

}