package ch.markwalther.starwars;

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.markwalther.starwars.movie.Movie


class MainListAdapter(private val data: List<Movie>) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById(R.id.main_list_item_text) as TextView

        val likeButton = itemView.findViewById(R.id.like_button) as Button
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_movie_list_item, parent, false)
        )
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.text = data[position].title
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = data.size
}
