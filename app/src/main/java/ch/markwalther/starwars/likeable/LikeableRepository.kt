package ch.markwalther.starwars.likeable

import android.content.SharedPreferences


class LikeableRepository(
	private val sharedPrefs: SharedPreferences
) {

	fun isLiked(id: Int) = sharedPrefs.getBoolean(id.toString(), false)

	fun storeLike(id: Int, checked: Boolean) {
		sharedPrefs.edit().putBoolean(id.toString(), checked).apply()
	}

}