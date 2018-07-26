package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test

import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.TestQuestion
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : BaseActivity() , TestAdapter.Listener{
    lateinit var adapter: TestAdapter
    val map:HashMap<Int,TestQuestion> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val questions = resources.getStringArray(R.array.questions_array)

        adapter = TestAdapter(questions,map,this)
        recyclerView.adapter = adapter

    }
    override fun onItemSelectedAt(position: Int, result: TestQuestion) {
        adapter?.setResult(position,result)
    }
}