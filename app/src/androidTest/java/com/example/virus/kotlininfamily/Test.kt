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
import android.view.View
import com.example.virus.kotlininfamily.R.id.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Test {
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
    fun clickTestForFirstCategory() {
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

        onView(withId(edit_name)).perform(typeText("infamily"))
        onView(withId(edit_mail)).perform(typeText("infamily@gmail.com"))
        onView(withId(edit_phone)).perform(typeText("0777777777"))
        onView(isRoot()).perform(pressBack())
        onView(withId(btn_auth)).perform(click())


        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
        Thread.sleep(300)
        onView(withId(btn_yes)).perform(click())
        onView(withId(btn_result)).perform(click())
        onView(isRoot()).perform(pressBack())
    }
    @Test
    fun clickTestForSecondCategory(){
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(1000)
        onView(isRoot()).perform(pressBack())
        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        for (i in 0 until 4) {
            onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))
            Thread.sleep(1000)
            onView(withId(categoryRecyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
            Thread.sleep(300)
            onView(isRoot()).perform(pressBack())
            onView(isRoot()).perform(pressBack())
                Thread.sleep(1000)
        }
        onView(withId(recyclerView)).perform(swipeUp())






    }

}