package com.example.virus.kotlininfamily.ui.main.section_for_child.categories

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.view.View
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistList
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.CATEGORY_ID
import com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle.ArticleActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.specialists.SpecialistActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.specialists.SpecialistPresenter
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.specialist_article.*

class CategoriesActivity : BaseActivity(), CategoryContract.View, CategoryAdapter.Listener{


    private lateinit var adapter: CategoryAdapter
    private lateinit var presenter: CategoryPresenter
    private lateinit var presenterSpecialist: SpecialistPresenter
    private lateinit var list:List<Categories>
    lateinit var specialistList:List<SpecialistList>
    var position:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setActionBarTitle()
        init()
    }

    private fun setActionBarTitle() {
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")

    }

    private fun init(){

        presenter = CategoryPresenter(this)
        presenter.getCategoriesById(intent.getIntExtra(Const.EXTRA_CATEGORY,1))
    }

    private fun initRecyclerView(result: List<Categories>) {
        adapter = CategoryAdapter(result,this)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryRecyclerView.adapter = adapter
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
