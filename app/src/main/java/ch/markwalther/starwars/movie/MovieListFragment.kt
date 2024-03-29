package ch.markwalther.starwars.movie

import ch.markwalther.starwars.Bean
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.likeable.LikeableListFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

class MovieListFragment : LikeableListFragment<Model.MovieList.Entry>(), KoinComponent {

	override fun getAdapter() =
		get<LikeableListAdapter<Model.MovieList.Entry>>(named(Bean.LikeableListAdapter.MOVIE))

	override fun getViewModel() = sharedViewModel<MovieViewModel>().value

}
