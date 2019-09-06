package ch.markwalther.starwars

import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.movie.MovieListAdapter
import ch.markwalther.starwars.movie.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

	private lateinit var adapter: MainListAdapter<out Model.Likeable>
	private lateinit var recyclerView: RecyclerView

	private val movieViewModel: MovieViewModel by inject()

	private val onNavigationItemSelectedListener =
		BottomNavigationView.OnNavigationItemSelectedListener { item ->
			when (item.itemId) {
				R.id.navigation_movies -> {
					loadMovies()
					return@OnNavigationItemSelectedListener true
				}
				R.id.navigation_characters -> {
					loadCharacters()
					return@OnNavigationItemSelectedListener true
				}
			}
			false
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

		// default tab is movies
		loadMovies()

		// liked-only switch
		findViewById<Switch>(R.id.liked_only_switch).setOnCheckedChangeListener { _, isChecked ->
			adapter.setFilterLikedOnly(isChecked)
		}
	}

	private fun loadMovies() {
		movieViewModel.all.observe(this, Observer { movies ->
			adapter = MovieListAdapter(movies.results)
			recyclerView.adapter = adapter
		})
	}

	private fun loadCharacters() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

}
