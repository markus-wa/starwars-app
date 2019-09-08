package ch.markwalther.starwars.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * API Client for swapi.co
 */
class SWApiClient(
	private val baseUrl: String
) {

	private val retrofit: Retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(baseUrl)
			.addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
			.build()
	}

	val movieService: MovieService by lazy {
		retrofit.create(MovieService::class.java)
	}

	val characterService: CharacterService by lazy {
		retrofit.create(CharacterService::class.java)
	}

}