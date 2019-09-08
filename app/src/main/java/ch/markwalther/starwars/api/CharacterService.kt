package ch.markwalther.starwars.api;

import retrofit2.http.GET

interface CharacterService {

	@GET("people")
	suspend fun all(): Model.CharacterList

}
