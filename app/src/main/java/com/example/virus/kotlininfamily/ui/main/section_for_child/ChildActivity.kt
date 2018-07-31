package com.example.virus.kotlininfamily.ui.main.section_for_child

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.MainMenuAdapter
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.ChildAdapter
import com.example.virus.kotlininfamily.ui.main.section_become_parent.ChildContract
import com.example.virus.kotlininfamily.ui.main.section_become_parent.ChildPresenter
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoriesActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoryContract
import com.example.virus.kotlininfamily.ui.main.section_for_child.specialists.SpecialistActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

val CATEGORY_TITL="ca"
var CATEGORY_ID=4

class ChildActivity: BaseActivity(), ChildAdapter.Listener ,ChildContract.View{
     var list: ArrayList<Categories> = ArrayList()
    lateinit var presenter: ChildPresenter
    lateinit var adapter :ChildAdapter



    private var pageId: Int = 1
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        supportActionBar?.title = resources.getString(R.string.category2)
        setContentView(R.layout.activity_main_menu)
        init()

    }


    override fun onSuccess(result: List<Categories>) {
        list = result as ArrayList<Categories>
        list.add(Categories(0,"Cписок специалистов","",""))
        initRecyclerView(result)
    }

    private fun initRecyclerView(result: ArrayList<Categories>) {
        adapter = ChildAdapter(result,this)
        recyclerView.adapter = adapter

    }

    fun init() {
        presenter = ChildPresenter(this)
        presenter.getMainMenuCategoryArticles(1)
    }

    override fun onItemSelectedAt(position: Int) {
        if(position == list.size-1){
                val intent = Intent(this, SpecialistActivity::class.java)
                startActivity(intent)
            }
            else{
        when(position){
            0-> CATEGORY_ID = 3
            1-> CATEGORY_ID = 4
            2-> CATEGORY_ID = 5
            3-> CATEGORY_ID = 17
            4-> CATEGORY_ID = 19
        }
        val intent=  Intent(this, CategoriesActivity::class.java)
        intent.putExtra(Const.EXTRA_CATEGORY, CATEGORY_ID)
            intent.putExtra("titleId",position)

            startActivity(intent)
    }

}
}