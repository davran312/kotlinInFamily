package com.example.virus.kotlininfamily.ui.main.section_for_parent.categories

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoriesAdapter
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoryContract
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categories.CategoryPresenter
import com.example.virus.kotlininfamily.ui.main.section_for_parent.categoriesArticle.ArticleActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.specialists.SpecialistActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

class CategoriesActivity : BaseActivity(), CategoryContract.View, CategoriesAdapter.Listener{


    private lateinit var adapter: CategoriesAdapter
    private lateinit var presenter: CategoryPresenter
    private lateinit var list:List<Categories>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        init()
    }
    private fun init(){
        presenter = CategoryPresenter(this)
        presenter.getCategoriesById(intent.getIntExtra(Const.EXTRA_CATEGORY,1))
    }
    private fun initRecyclerView(result: List<Categories>) {
        adapter = CategoriesAdapter(result,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onSuccess(result: List<Categories>) {
        initRecyclerView(result)
        list = result


    }
    override fun onItemSelectedAt(position: Int) {

        var intent = Intent(this,ArticleActivity::class.java)
        intent.putExtra(Const.EXTRA_SERIALIZABLE,list.get(position))
        startActivity(intent)
        }
    }
