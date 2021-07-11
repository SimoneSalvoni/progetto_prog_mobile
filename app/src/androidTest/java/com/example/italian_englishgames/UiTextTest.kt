package com.example.italian_englishgames

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.italian_englishgames.auth.LoginActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class UiTextTest {

    private lateinit var stringTypedLogin: String

    @get:Rule
    var loginActivityRule: ActivityScenarioRule<LoginActivity> =
        ActivityScenarioRule(LoginActivity::class.java)



    @Before
    fun setUp() {
        stringTypedLogin = "email"
    }

    @Test
    fun typeInLoginTest() {
        onView(withId(R.id.email)).perform(typeText(stringTypedLogin), closeSoftKeyboard())
        onView(withId(R.id.email)).check(matches(withText("email")))
    }

}

