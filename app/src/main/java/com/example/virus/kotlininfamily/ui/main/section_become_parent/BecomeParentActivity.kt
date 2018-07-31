package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization.LoginActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.InformationActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test.TestActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_main_menu.*

class BecomeParentActivity: BaseActivity(),ChildAdapter.Listener,ChildContract.View {
    lateinit var list: List<Categories>
    lateinit var presenter: ChildPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        init()

    }

    override fun onSuccess(result: List<Categories>) {
        initRecyclerView()
        list = result
    }

    private fun initRecyclerView() {
        recyclerView.adapter = ChildAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)

    }

    fun init() {
        presenter = ChildPresenter(this)
        presenter.getMainMenuCategoryArticles(3)
    }

    override fun onItemSelectedAt(position: Int) {
        if(userInfoIsEmpty()){
            startActivity(Intent(this,LoginActivity::class.java))
        }
        else{
        startActivity(Intent(this, when (position) {
            0 -> InformationActivity::class.java
            1 -> DocumentsActivity::class.java
            else -> TestActivity::class.java
        }))

    }
    }


    private fun userInfoIsEmpty(): Boolean {
        val array: ArrayList<String>? = FileUtils.readCacheData(this, Const.USER_AUTH_INFORMATION)
        return array == null
    }
}