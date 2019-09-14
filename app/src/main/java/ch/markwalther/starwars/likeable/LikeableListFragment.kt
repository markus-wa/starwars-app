package ch.markwalther.starwars.likeable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import ch.markwalther.starwars.R
import ch.markwalther.starwars.api.Model
import kotlinx.android.synthetic.main.fragment_list.*

// we can't use composition because android is terrible
// and can't instantiate generic fragments with arguments
abstract class LikeableListFragment<T : Model.Likeable> : Fragment() {

	private object Key {
		const val LIKED_ONLY = "liked_only"
	}

	private lateinit var adapter: LikeableListAdapter<T>

	private lateinit var viewModel: LikeableViewModel<T>

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		adapter = getAdapter()
		viewModel = getViewModel()

		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_list, container, false)
	}

	abstract fun getAdapter(): LikeableListAdapter<T>

	abstract fun getViewModel(): LikeableViewModel<T>

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// recycler view
		mainRecyclerView.apply {
			// use this setting to improve performance if you know that changes
			// in content do not change the layout size of the RecyclerView
			setHasFixedSize(true)

			// use a linear layout manager
			layoutManager = LinearLayoutManager(this@LikeableListFragment.context)

			adapter = this@LikeableListFragment.adapter
		}

		loadData()

		// liked-only switch
		likedOnlySwich.setOnCheckedChangeListener { _, isChecked ->
			adapter.displayLikedOnly = isChecked
		}
	}

	private fun loadData() {
		viewModel.all.observe(this, Observer { data ->
			adapter.data = data
		})
	}

	override fun onSaveInstanceState(outState: Bundle) {
		outState.putBoolean(Key.LIKED_ONLY, likedOnlySwich.isChecked)
		super.onSaveInstanceState(outState)
	}

	override fun onViewStateRestored(inState: Bundle?) {
		super.onViewStateRestored(inState)
		inState?.let {
			likedOnlySwich.isChecked = it.getBoolean(Key.LIKED_ONLY)
		}
	}

}
