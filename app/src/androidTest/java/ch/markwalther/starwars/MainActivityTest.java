package ch.markwalther.starwars;

import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(
            MainActivity.class,
            true,
            false);   // false to customize the intent

    @Test
    public void intent() {
        Intent intent = new Intent();
        //intent.putExtra("your_key", "your_value");

        activityRule.launchActivity(intent);

        // TODO: assertions
    }

}