package ch.markwalther.starwars.movie

import android.content.SharedPreferences
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.api.MovieService

class MovieRepository(
	private val service: MovieService,
	private val sharedPrefs: SharedPreferences
) {

	suspend fun all(): Model.MovieList {
		val apiRes = service.all().results
		val entries = loadLikes(apiRes)
		return Model.MovieList(entries)
	}

	private fun loadLikes(apiRes: List<Model.MovieList.Entry>): List<Model.MovieList.Entry> {
		return apiRes.mapIndexed { i, it ->
			// results are ordered by release date and the index (starting at 1) represents the ID
			val id = i + 1
			val liked = sharedPrefs.getBoolean(id.toString(), false)
			Model.MovieList.Entry(id, it.title, liked)
		}
	}

	fun storeLike(id: Int, checked: Boolean) {
		sharedPrefs.edit().putBoolean(id.toString(), checked).apply()
	}

}