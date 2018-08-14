package com.example.virus.kotlininfamily.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.section_become_parent.BecomeParentActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.ChildActivity
import com.example.virus.kotlininfamily.ui.main.section_for_parent.ParentActivity
import kotlinx.android.synthetic.main.activity_header_menu.*

@Suppress("DEPRECATION")
class MainMenuActivity : AppCompatActivity()     {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header_menu)
        setButtonClickListeners()
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")
    }

    private fun setButtonClickListeners() {
        button1.setOnClickListener {
            startActivity(Intent(this,BecomeParentActivity::class.java))
        }
        button2.setOnClickListener {
            startActivity(Intent(this,ChildActivity::class.java))
        }
        button3.setOnClickListener {
            startActivity(Intent(this,ParentActivity::class.java))
        }
    }


}
