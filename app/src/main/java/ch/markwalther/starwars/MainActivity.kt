package ch.markwalther.starwars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import ch.markwalther.starwars.character.CharacterViewModel
import ch.markwalther.starwars.movie.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

	private val movieViewModel by viewModel<MovieViewModel>()

	private val characterViewModel by viewModel<CharacterViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// nav
		navView.setupWithNavController(findNavController(R.id.navHostFragment))
	}

}
