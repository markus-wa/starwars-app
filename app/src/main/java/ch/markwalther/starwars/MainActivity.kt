package ch.markwalther.starwars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.movie.MovieViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    //textMessage.setText(R.string.title_movies)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    //textMessage.setText(R.string.title_characters)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    //textMessage.setText(R.string.title_planets)
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
        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager
        }

        val viewModel = MovieViewModel()
        viewModel.all.observe(this, Observer { movies ->
            recyclerView.adapter = MainListAdapter(movies.results)
        })
    }

}
