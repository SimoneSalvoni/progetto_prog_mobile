package com.example.italian_englishgames

import android.app.Activity
import android.content.Intent
import android.net.Uri
import org.hamcrest.Matchers.not
import android.app.Instrumentation.ActivityResult
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.matcher.IntentMatchers.isInternal
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class IntentTest {

    @get:Rule
    var contattiActivityRule: IntentsTestRule<ContattiActivity> =
        IntentsTestRule(ContattiActivity::class.java)

    @Before
    fun stubAllExternalIntents() {
        //blocca gli intent esterni all'app facendole ritornare subito
        intending(not(isInternal())).respondWith(ActivityResult(Activity.RESULT_OK, null))
    }

    @Test
    fun intentStartTest() {
        Espresso.onView(ViewMatchers.withId(R.id.imageEmail)).perform(ViewActions.click())
        Intents.intended(
            AllOf.allOf(
                IntentMatchers.hasAction(Intent.ACTION_SENDTO),
                IntentMatchers.hasData(Uri.parse("mailto:"))
            )
        )
    }
}