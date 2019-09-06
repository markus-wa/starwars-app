package ch.markwalther.starwars.api;

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

	@GET("films/{id}")
	fun get(): Call<Model.Movie>

	@GET("films")
	suspend fun all(): Model.MovieList

}
