package ch.markwalther.starwars.character

import ch.markwalther.starwars.Bean
import ch.markwalther.starwars.api.Model
import ch.markwalther.starwars.likeable.LikeableListAdapter
import ch.markwalther.starwars.likeable.LikeableListFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.qualifier.named

class CharacterListFragment : LikeableListFragment<Model.CharacterList.Entry>(), KoinComponent {

	override fun getAdapter() =
		get<LikeableListAdapter<Model.CharacterList.Entry>>(named(Bean.LikeableListAdapter.CHARACTERS))

	override fun getViewModel() = sharedViewModel<CharacterViewModel>().value

}
