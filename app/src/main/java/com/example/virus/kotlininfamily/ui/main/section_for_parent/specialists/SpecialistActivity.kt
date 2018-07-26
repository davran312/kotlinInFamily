package com.example.virus.kotlininfamily.ui.main.section_for_parent.specialists

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistList
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoriesAdapter
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoryContract
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoryPresenter
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categoriesArticle.ArticleActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

class SpecialistActivity :BaseActivity(), SpecialistContract.View,SpecialistAdapter.Listener{


    private lateinit var adapter: SpecialistAdapter
        private lateinit var presenter: SpecialistPresenter
        private lateinit var list:List<SpecialistList>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main_menu)
            init()
        }
        private fun init(){
            presenter = SpecialistPresenter(this)
            presenter.getSpecialistList()

        }
        private fun initRecyclerView(result: List<SpecialistList>) {
            val imageList = getImageList()
            adapter = SpecialistAdapter(result,this,imageList)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        }

    private fun getImageList(): ArrayList<Int> {
        var list: ArrayList<Int> = ArrayList()
        list.add(R.drawable.psiholog)
        list.add(R.drawable.medik)
        list.add(R.drawable.rabotniki)
        list.add(R.drawable.ginekolog)
        list.add(R.drawable.uristi)
        return list
    }

    override fun onSuccess(result: List<SpecialistList>) {
        initRecyclerView(result)
        list = result
    }

    override fun onItemSelectedAt(position: Int) {

        }
    }


