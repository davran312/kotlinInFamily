package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.sections.SectionFirst
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.sections.SectionSecond
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.sections.SectionThird

internal class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){
    override fun getItem(position:Int): Fragment {
        return when(position){
            1-> SectionSecond()
            2-> SectionThird()
            else -> SectionFirst()
        }
    }
    override fun getCount():Int = 3
}