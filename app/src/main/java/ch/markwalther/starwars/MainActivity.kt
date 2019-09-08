package ch.markwalther.starwars

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.character.CharacterListAdapter
import ch.markwalther.starwars.character.CharacterViewModel
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.movie.MovieListAdapter
import ch.markwalther.starwars.movie.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : AppCompatActivity() {

	companion object {
		const val KEY_LIKED_ONLY = "liked_only"
		const val KEY_CURRENT_NAV_TAB = "current_nav_tab"
	}

	private val movieViewModel: MovieViewModel by inject()
	private val characterViewModel: CharacterViewModel by inject()

	private var currentNavTab = R.id.navigation_movies

	private lateinit var adapter: LikeableListAdapter<out Model.Likeable>
	private lateinit var recyclerView: RecyclerView

	private val onNavigationItemSelectedListener =
		BottomNavigationView.OnNavigationItemSelectedListener { item ->
			navigate(item.itemId)
		}

	private fun navigate(itemId: Int): Boolean {
		currentNavTab = itemId
		when (itemId) {
			R.id.navigation_movies -> {
				loadMovies()
				return true
			}
			R.id.navigation_characters -> {
				loadCharacters()
				return true
			}
		}
		return false
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// nav
		val navView: BottomNavigationView = findViewById(R.id.nav_view)
		navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

		// recycler view
		recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
			// use this setting to improve performance if you know that changes
			// in content do not change the layout size of the RecyclerView
			setHasFixedSize(true)

			// use a linear layout manager
			layoutManager = LinearLayoutManager(this@MainActivity)
		}

		adapter = MovieListAdapter(get(named(LIKEABLE_BEAN_MOVIES)), listOf())

		loadMovies()

		// liked-only switch
		findViewById<Switch>(R.id.liked_only_switch).setOnCheckedChangeListener { _, isChecked ->
			adapter.setFilterLikedOnly(isChecked)
		}
	}

	private fun loadMovies() {
		// prevent data race
		characterViewModel.all.removeObservers(this)

		movieViewModel.all.observe(this, Observer { movies ->
			setAdapter(
				MovieListAdapter(
					get(named(LIKEABLE_BEAN_MOVIES)),
					movies.results
				)
			)
		})
	}

	private fun loadCharacters() {
		// prevent data race
		movieViewModel.all.removeObservers(this)

		characterViewModel.all.observe(this, Observer { characters ->
			setAdapter(
				CharacterListAdapter(
					get(named(LIKEABLE_BEAN_CHARACTERS)),
					characters.results
				)
			)
		})
	}

	private fun setAdapter(newAdapter: LikeableListAdapter<out Model.Likeable>) {
		newAdapter.setFilterLikedOnly(likedOnly())
		adapter = newAdapter
		recyclerView.adapter = newAdapter
	}

	private fun likedOnly() = findViewById<Switch>(R.id.liked_only_switch).isChecked

	override fun onSaveInstanceState(outState: Bundle) {
		outState.putInt(KEY_CURRENT_NAV_TAB, currentNavTab)
		outState.putBoolean(KEY_LIKED_ONLY, likedOnly())
		super.onSaveInstanceState(outState)
	}

	override fun onRestoreInstanceState(inState: Bundle) {
		super.onRestoreInstanceState(inState)
		navigate(inState.getInt(KEY_CURRENT_NAV_TAB))
		findViewById<Switch>(R.id.liked_only_switch).isChecked = inState.getBoolean(KEY_LIKED_ONLY)
	}

}
