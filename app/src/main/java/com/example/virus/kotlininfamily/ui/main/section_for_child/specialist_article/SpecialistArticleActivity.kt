package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_article.*

class SpecialistArticleActivity :BaseActivity(),SpecialistArticleContract.View{

    private lateinit var adapter: SpecialistArticleAdapter
    private lateinit var specialistArticle: SpecialistArticle
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
        specialistArticle = result
    }
    fun maps(view:View){
            var uri=Uri.parse("geo:<"+ lat+">,<"+ long+">?q=<"+ lat+">,<"+ long+">("+ labelLocation+")0")
            var intent=Intent(Intent.ACTION_VIEW,uri)
            intent.setPackage("com.google.android.apps.maps")
        if (long != null && lat!=null) {
            startActivity(intent)
        }
    }
    private fun initRecyclerView(result: SpecialistArticle) {
        adapter = SpecialistArticleAdapter(result)
        recyclerViewForArticle.layoutManager = LinearLayoutManager(this)
        recyclerViewForArticle.adapter = adapter

    }
}