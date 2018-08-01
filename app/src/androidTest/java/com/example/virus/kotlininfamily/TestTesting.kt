package com.example.virus.kotlininfamily

import android.content.pm.ActivityInfo
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
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
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import com.example.virus.kotlininfamily.R.id.*
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test.TestActivity

//@RunWith(AndroidJUnit4::class)
//class TestTesting {
//    @get:Rule
//    val mainRule = ActivityTestRule(
//            TestActivity::class.java)
//    @Test
//    fun runTest(){
//        onView(withId(R.id.btn_result)).perform(ViewActions.click())
//        Thread.sleep(2000)
//        for (i in 0..14){
//        onView(withId(recyclerView)).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(i, click()))
//       }
//        onView(withId(R.id.btn_result)).perform(ViewActions.click())
//
//
//    }
//}