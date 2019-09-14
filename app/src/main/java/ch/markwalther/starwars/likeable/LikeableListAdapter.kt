package ch.markwalther.starwars.likeable

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.R
import ch.markwalther.starwars.api.Model
import org.koin.core.KoinComponent

class LikeableListAdapter<T : Model.Likeable>(
	private val repository: LikeableRepository,
	private val textGen: TextGenerator<T>,
	data: List<T> = listOf()
) : RecyclerView.Adapter<LikeableListAdapter.ViewHolder>(), KoinComponent {

	abstract class TextGenerator<T> {

		abstract fun getText(likeable: T): String

	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

		constructor(parent: ViewGroup) : this(
			LayoutInflater.from(parent.context)
				.inflate(R.layout.layout_main_list_item, parent, false)
		)

		val textView: TextView = itemView.findViewById(R.id.main_list_item_text)

		val likeButton: ToggleButton = itemView.findViewById(R.id.like_button)

	}

	var data = data
		get() = field.filter { !displayLikedOnly || it.liked }
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	var displayLikedOnly = false
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	// Create new views (invoked by the layout manager)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

	// Set the contents of a view (invoked by the layout manager)
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val likeable = data[position]
		holder.textView.text = textGen.getText(likeable)

		holder.likeButton.setOnCheckedChangeListener(null)
		holder.likeButton.isChecked = likeable.liked

		holder.likeButton.setOnCheckedChangeListener { _, isChecked ->
			likeable.liked = isChecked
			repository.storeLike(likeable.id, isChecked)
		}
	}

	// Return the size of your dataset (invoked by the layout manager)
	override fun getItemCount() = data.size

}
