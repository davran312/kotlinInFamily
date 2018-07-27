package com.example.virus.kotlininfamily.ui.main.section_for_parent

import android.content.Intent
import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.MainMenuAdapter
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoriesActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_header_menu.*

class ParentActivity :BaseActivity(),MainMenuAdapter.Listener {
    var CATEGORY_ID=4


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_menu)
        supportActionBar?.title = resources.getString(R.string.category3)

    val list = ArrayList<Category>()
    list.add(Category(getString(R.string.advices), R.drawable.conflict))
    list.add(Category(getString(R.string.save_understanding), R.drawable.vzaimoponimanir))
    list.add(Category(getString(R.string.dosug), R.drawable.dosug))
    recyclerView.adapter = MainMenuAdapter(list,this)
    }
    override fun onItemSelectedAt(position: Int) {
        when(position){
            0-> CATEGORY_ID =7
            1-> CATEGORY_ID = 9
            2-> CATEGORY_ID = 20
        }

        val intent=  Intent(this, CategoriesActivity::class.java)
        intent.putExtra(Const.EXTRA_CATEGORY,CATEGORY_ID)
        startActivity(intent)

    }

}