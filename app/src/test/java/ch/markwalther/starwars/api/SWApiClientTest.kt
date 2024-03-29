package ch.markwalther.starwars.api

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SWApiClientTest {

	private lateinit var wireMock: WireMockServer

	@BeforeEach
	fun beforeEach() {
		wireMock = WireMockServer(wireMockConfig().dynamicPort())
		wireMock.start()
	}

	@AfterEach
	fun afterEach() {
		wireMock.stop()
	}

	@Test
	fun movieService() {
		wireMock.stubFor(
			get(urlEqualTo("/api/films"))
				.willReturn(
					aResponse()
						.withHeader("Content-Type", "text/json")
						.withStatus(200)
						.withBodyFile("json/films.json")
				)
		)

		val client = SWApiClient("http://localhost:" + wireMock.port() + "/api/")
		val result = runBlocking { client.movieService.all() }

		assertThat(result).isEqualTo(
			Model.MovieList(
				listOf(
					Model.MovieList.Entry(0, "A New Hope", false),
					Model.MovieList.Entry(0, "Attack of the Clones", false),
					Model.MovieList.Entry(0, "The Phantom Menace", false),
					Model.MovieList.Entry(0, "Revenge of the Sith", false),
					Model.MovieList.Entry(0, "Return of the Jedi", false),
					Model.MovieList.Entry(0, "The Empire Strikes Back", false),
					Model.MovieList.Entry(0, "The Force Awakens", false)
				)
			)
		)
	}

	@Nested
	inner class CharacterService {

		@Test
		fun all() {
			wireMock.stubFor(
				get(urlEqualTo("/api/people"))
					.willReturn(
						aResponse()
							.withHeader("Content-Type", "text/json")
							.withStatus(200)
							.withBodyFile("json/people.json")
					)
			)

			val client = SWApiClient("http://localhost:" + wireMock.port() + "/api/")
			val result = runBlocking { client.characterService.all() }

			val first5Characters = result.copy(results = result.results.subList(0, 5))
			assertThat(first5Characters).isEqualTo(
				Model.CharacterList(
					listOf(
						Model.CharacterList.Entry(0, "Luke Skywalker", false),
						Model.CharacterList.Entry(0, "C-3PO", false),
						Model.CharacterList.Entry(0, "R2-D2", false),
						Model.CharacterList.Entry(0, "Darth Vader", false),
						Model.CharacterList.Entry(0, "Leia Organa", false)
					)
				)
			)
		}

	}

}