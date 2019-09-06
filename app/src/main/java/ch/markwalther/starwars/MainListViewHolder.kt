package ch.markwalther.starwars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView

class MainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	constructor(parent: ViewGroup) : this(
		LayoutInflater.from(parent.context)
			.inflate(R.layout.layout_main_list_item, parent, false)
	)

	val textView: TextView = itemView.findViewById(R.id.main_list_item_text)

	val likeButton: ToggleButton = itemView.findViewById(R.id.like_button)
}