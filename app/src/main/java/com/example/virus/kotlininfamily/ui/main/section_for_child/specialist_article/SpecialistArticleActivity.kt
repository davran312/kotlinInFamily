package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoriesAdapter
import com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle.ArticleAdapter
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_article.*

class SpecialistArticleActivity :BaseActivity(),SpecialistArticleContract.View{

    private lateinit var adapter: SpecialistArticleAdapter
    private lateinit var item: SpecialistArticle
    private  var presenter: SpecialistArticlePresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        init()

            }

    private fun init() {
        presenter = SpecialistArticlePresenter(this)
        presenter!!.getSpecialistArticleById(intent.getIntExtra(Const.EXTRA_SPECIALIST_ID,0))

    }

    override fun onSuccess(result: SpecialistArticle) {
        initRecyclerView(result)
        item = result
    }

    private fun initRecyclerView(result: SpecialistArticle) {
        adapter = SpecialistArticleAdapter(result)
        recyclerViewForArticle.layoutManager = LinearLayoutManager(this)
        recyclerViewForArticle.adapter = adapter

    }
}