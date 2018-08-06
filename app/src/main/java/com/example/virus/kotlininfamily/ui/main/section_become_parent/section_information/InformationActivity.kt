package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : BaseActivity(){
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mViewPager?.adapter = mSectionsPagerAdapter
        mViewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(mViewPager))

    }
}