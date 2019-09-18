package ch.markwalther.starwars

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

	@Rule
	var activityRule = ActivityTestRule(
		MainActivity::class.java,
		true,
		false
	)   // false to customize the intent

	@Test
	fun intent() {
		val intent = Intent()
		//intent.putExtra("your_key", "your_value");

		activityRule.launchActivity(intent)

		// TODO: assertions
	}

}