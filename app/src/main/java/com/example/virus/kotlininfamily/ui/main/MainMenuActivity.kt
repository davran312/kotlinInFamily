package com.example.virus.kotlininfamily.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.section_become_parent.BecomeParentActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.ChildActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.ParentActivity
import kotlinx.android.synthetic.main.activity_main_menu.*
@Suppress("DEPRECATION")
class MainMenuActivity : AppCompatActivity(), MainMenuAdapter.Listener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_menu)
        supportActionBar?.title = Html.fromHtml("<font color=\"red\">" + getString(R.string.app_name) + "</font>")
        val list = ArrayList<Category>()
        list.add(Category(getString(R.string.category1), R.drawable.first))
        list.add(Category(getString(R.string.category2), R.drawable.second))
        list.add(Category(getString(R.string.category3), R.drawable.third))
        recyclerView.adapter = MainMenuAdapter(list,this)
    }
    override fun onItemSelectedAt(position: Int) {
        startActivity(Intent(this,when(position){
            0-> BecomeParentActivity::class.java
            1-> ChildActivity::class.java
            else -> ParentActivity::class.java
        }))
    }
}
