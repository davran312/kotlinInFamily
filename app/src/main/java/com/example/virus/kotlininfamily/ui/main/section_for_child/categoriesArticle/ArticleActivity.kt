package com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.support.v7.widget.ToolbarWidgetWrapper
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_article.*
import kotlinx.android.synthetic.main.category_article.*

class ArticleActivity : BaseActivity() {
    private lateinit var adapter: ArticleAdapter
    private lateinit var item: Categories


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        setActionBarTitle()

        item = intent.getSerializableExtra(Const.EXTRA_SERIALIZABLE) as Categories
        adapter = ArticleAdapter(item)
        recyclerViewForArticle.adapter = adapter
    }
    private fun setActionBarTitle() {
       supportActionBar?.setDisplayShowHomeEnabled(true)

    }
}