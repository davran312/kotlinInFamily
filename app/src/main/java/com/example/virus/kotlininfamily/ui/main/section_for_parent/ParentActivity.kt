package com.example.virus.kotlininfamily.ui.main.section_for_parent

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.*
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoriesActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

@Suppress("DEPRECATION")
class ParentActivity :BaseActivity(), ParentAdapter.Listener,ParentContract.View {
    var CATEGORY_ID=4
    lateinit var list: List<Categories>
    lateinit var presenter: ParentPresenter
    lateinit var adapter:ParentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")


        init()
    }


    override fun onSuccess(result: List<Categories>) {
        list = result
        initRecyclerView(list)
    }

    private fun initRecyclerView(result: List<Categories>) {
        adapter = ParentAdapter(result,this)
        recyclerView.adapter = adapter

    }

    fun init() {
        presenter = ParentPresenter(this)
        presenter.getMainMenuCategoryArticles(2)
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