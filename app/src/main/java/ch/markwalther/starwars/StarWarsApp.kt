package ch.markwalther.starwars

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
class StarWarsApp : Application() {

	override fun onCreate() {
		super.onCreate()

		// start Koin!
		startKoin {
			androidContext(this@StarWarsApp)
			androidLogger()
			modules(koinStarWarsModule)
		}
	}

}