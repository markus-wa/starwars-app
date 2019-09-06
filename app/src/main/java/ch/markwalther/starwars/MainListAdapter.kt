package ch.markwalther.starwars

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.api.Model

abstract class MainListAdapter<T : Model.Likeable>(
	data: List<T>
) : RecyclerView.Adapter<MainListViewHolder>() {

	private var displayLikedOnly = false

	var data = data
		get() = field.filter { !displayLikedOnly || it.liked }

	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainListViewHolder(parent)

	fun setFilterLikedOnly(displayLikedOnly: Boolean) {
		this.displayLikedOnly = displayLikedOnly
		notifyDataSetChanged()
	}

	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = data.size

}
