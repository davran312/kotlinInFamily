package com.example.virus.kotlininfamily

import android.content.pm.ActivityInfo
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.example.virus.kotlininfamily.R.id.recyclerView
import com.example.virus.kotlininfamily.ui.main.MainMenuActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.ParentActivity
import kotlinx.android.synthetic.main.activity_header_menu.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class UiTest {
    @get:Rule
    val mainRule = ActivityTestRule(
            MainMenuActivity::class.java)

    @Test
    fun testOnClickForParents() {


        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()))
        for(i in 0  until 3) {

    fun orientationTest() {
        onView(withId(R.id.recyclerView)).perform(swipeRight())
        mainRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        mainRule.getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    @Test
    fun clickTest() {
        onView(isRoot()).perform(pressBack())
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        onView(isRoot()).perform(pressBack())
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        for (i in 0..2) {
            onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))
            onView(withId(R.id.categoryRecyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            onView(withId(R.id.tw_article_title)).check(matches(isDisplayed()))
            onView(withId(R.id.tw_article_text)).check(matches(isDisplayed()))
            onView(isRoot()).perform(pressBack())
        }
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        for (i in 0..2) {
            onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))
            Thread.sleep(1000)
            onView(isRoot()).perform(pressBack())

        }

    }

        }


        onView(withId(recyclerView)).perform(swipeUp())
    }
}

