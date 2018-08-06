package com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle

import android.os.Bundle
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_article.*

class ArticleActivity : BaseActivity() {
    private lateinit var adapter: ArticleAdapter
    private lateinit var item: Categories



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setActionBarTitle()

        item = intent.getSerializableExtra(Const.EXTRA_SERIALIZABLE) as Categories
        adapter = ArticleAdapter(item)
        recyclerViewForArticle.adapter = adapter
    }
    private fun setActionBarTitle() {
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")

    }
}