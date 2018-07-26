package com.example.virus.kotlininfamily.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.section_become_parent.BecomeParentActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.ParentActivity
import kotlinx.android.synthetic.main.activity_main_menu.*

class MainMenuActivity : AppCompatActivity(), MainMenuAdapter.Listener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_menu)


        val list = ArrayList<Category>()
        list.add(Category(getString(R.string.category1), R.drawable.first_section))
        list.add(Category(getString(R.string.category2), R.drawable.second_section))
        list.add(Category(getString(R.string.category3), R.drawable.third_section))
        recyclerView.adapter = MainMenuAdapter(list,this)
    }
    override fun onItemSelectedAt(position: Int) {
        startActivity(Intent(this,when(position){
            0->BecomeParentActivity::class.java
            1-> ParentActivity::class.java
            else -> BecomeParentActivity::class.java
        }))
    }
}
