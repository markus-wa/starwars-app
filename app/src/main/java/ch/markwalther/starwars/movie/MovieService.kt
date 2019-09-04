package ch.markwalther.starwars.movie;

import retrofit2.Call
import retrofit2.http.GET

interface MovieService {

    @GET("films/{id}")
    fun get(): Call<Movie>

    @GET("films")
    suspend fun all(): MovieList

}
