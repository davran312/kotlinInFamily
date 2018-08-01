package com.example.virus.kotlininfamily

import android.content.pm.ActivityInfo
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.example.virus.kotlininfamily.ui.main.MainMenuActivity
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import org.junit.Test
import org.junit.runner.RunWith
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main_menu.*
import org.junit.Assert.*
import org.junit.Rule
import java.util.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import com.example.virus.kotlininfamily.R.id.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class FirstCategoryTest {
    @get:Rule
    val mainRule = ActivityTestRule(
            MainMenuActivity::class.java)

    @Test
    fun orientationTest() {
        onView(withId(R.id.recyclerView)).perform(swipeRight())
        mainRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        mainRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    @Test
    fun clickTest() {
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(isRoot()).perform(pressBack())
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            Thread.sleep(1000)
            onView(withId(mViewPager)).perform(swipeLeft())

        Thread.sleep(300)
            onView(withId(mViewPager)).perform(swipeLeft())

        Thread.sleep(300)
            onView(withId(mViewPager)).perform(swipeRight())

        Thread.sleep(300)
            onView(withId(mViewPager)).perform(swipeRight())

        Thread.sleep(300)
            onView(isRoot()).perform(pressBack())


            onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(400)

        onView(isRoot()).perform(pressBack())

        onView(withId(recyclerView)).perform(swipeUp())
    }
}