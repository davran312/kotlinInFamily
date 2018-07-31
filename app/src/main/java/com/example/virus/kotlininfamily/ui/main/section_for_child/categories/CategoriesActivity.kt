package com.example.virus.kotlininfamily.ui.main.section_for_child.categories

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.categoriesArticle.ArticleActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_categories.*
import kotlinx.android.synthetic.main.activity_main_menu.*

class CategoriesActivity : BaseActivity(), CategoryContract.View, CategoryAdapter.Listener{


    private lateinit var adapter: CategoryAdapter
    private lateinit var presenter: CategoryPresenter
    private lateinit var list:List<Categories>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)
        setActionBarTitle()
        init()
    }

    private fun setActionBarTitle() {
        supportActionBar?.title = when(intent.getIntExtra("titleId",0)){
            0-> "Часто задаваемые вопросы"
            1-> "Ресурсы по воспитанию детей"
            2-> "Поддержка экспертов"
            3-> "Досуг для детей"
            4-> "Список экспертов"
            else ->"InFamily"
        }
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
