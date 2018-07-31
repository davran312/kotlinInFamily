package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bumptech.glide.Glide.init
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_main_menu.*

class BecomeParentActivity: BaseActivity(),BecomeParentAdapter.Listener,BecomeParentContract.View{
    lateinit var list: List<Categories>
    lateinit var presenter:BecomeParentContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        init()

    }
    override fun onSuccess(result: List<Categories>) {
        initRecyclerView()
        list = result
    }

    private fun initRecyclerView() {
        recyclerView.adapter = BecomeParentAdapter(list,this)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun init(){
        presenter = BecomeParentPresenter(this)
        presenter.getMainMenuCategoryArticles(3)
    }

    override fun onItemSelectedAt(position: Int) {
    }


    private fun userInfoIsEmpty(): Boolean {
        val array :ArrayList<String>? = FileUtils.readCacheData(this,Const.USER_AUTH_INFORMATION)
        return array == null
    }
}