package com.example.virus.kotlininfamily.ui.main.section_for_child.SpecialistNames

import android.content.Intent
import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.models.SpecialistList
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article.SpecialistArticleActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

class SpecialistNamesActivity : BaseActivity() , SpecialistNamesAdapter.Listener {

    private lateinit var adapter: SpecialistNamesAdapter
    private lateinit var item: SpecialistList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
            item = intent.getSerializableExtra(Const.EXTRA_SERIALIZABLE) as SpecialistList
            adapter = SpecialistNamesAdapter(item.people, this)
            recyclerView.adapter = adapter
        }
    override fun onItemSelectedAt(position: Int) {
        val intent = Intent(this,SpecialistArticleActivity::class.java)
        intent.putExtra(Const.EXTRA_SPECIALIST_ID,item.people.get(position).id)
        startActivity(intent)
    }

}


