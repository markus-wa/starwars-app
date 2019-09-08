package ch.markwalther.starwars.likeable

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.api.Model
import org.koin.core.KoinComponent

abstract class LikeableListAdapter<T : Model.Likeable>(
	private val repository: LikeableRepository,
	data: List<T>
) : RecyclerView.Adapter<LikeableListViewHolder>(), KoinComponent {

	private var displayLikedOnly = false

	var data = data
		get() = field.filter { !displayLikedOnly || it.liked }

	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
		LikeableListViewHolder(parent)

	// Set the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(holder: LikeableListViewHolder, position: Int) {
		val likeable = data[position]
		holder.textView.text = getText(likeable)

		holder.likeButton.setOnCheckedChangeListener(null)
		holder.likeButton.isChecked = repository.isLiked(likeable.id)

		holder.likeButton.setOnCheckedChangeListener { _, isChecked ->
			likeable.liked = isChecked
			repository.storeLike(likeable.id, isChecked)
		}
	}

	abstract fun getText(likeable: T): String

	fun setFilterLikedOnly(displayLikedOnly: Boolean) {
		this.displayLikedOnly = displayLikedOnly
		notifyDataSetChanged()
	}

	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = data.size

}
