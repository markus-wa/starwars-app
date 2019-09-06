package ch.markwalther.starwars.movie

import ch.markwalther.starwars.MainListAdapter
import ch.markwalther.starwars.MainListViewHolder
import ch.markwalther.starwars.api.Model
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieListAdapter(
	data: List<Model.MovieList.Entry>
) : MainListAdapter<Model.MovieList.Entry>(data), KoinComponent {

	private val repository: MovieRepository by inject()

	// Set the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
		val movie = data[position]
		holder.textView.text = movie.title

		holder.likeButton.setOnCheckedChangeListener(null)
		holder.likeButton.isChecked = movie.liked

		holder.likeButton.setOnCheckedChangeListener { _, isChecked ->
			movie.liked = isChecked
			repository.storeLike(movie.id, isChecked)
		}
	}

}
