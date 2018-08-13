package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.TestQuestion
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*
import java.sql.Array

class TestActivity : BaseActivity() , TestAdapter.Listener{
    lateinit var adapter: TestAdapter
    val map:HashMap<Int,TestQuestion> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")
        val questions = resources.getStringArray(R.array.questions_array)

        adapter = TestAdapter(questions,map,this)
        btn_result.setOnClickListener{
            if(adapter.map.size == questions!!.size) {
                val intent = Intent(this, TestResultActivity::class.java)
                intent.putExtra("map", adapter.map)
                finish()
                startActivity(intent)
            }else{
                Toast.makeText(this,"Вы ответили не на все вопросы",Toast.LENGTH_LONG).show()
            }
        }
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter


    }
    override fun onItemSelectedAt(position: Int, result: TestQuestion) {
        adapter?.setResult(position,result)
    }
}