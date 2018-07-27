package com.example.virus.kotlininfamily.ui.main.section_for_child

import android.content.Intent
import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Category
import com.example.virus.kotlininfamily.ui.main.MainMenuAdapter
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoriesActivity
import com.example.virus.kotlininfamily.ui.main.section_for_child.categories.CategoryContract
import com.example.virus.kotlininfamily.ui.main.section_for_child.specialists.SpecialistActivity
import com.example.virus.kotlininfamily.utils.Const
import kotlinx.android.synthetic.main.activity_main_menu.*

val CATEGORY_TITL="ca"
var CATEGORY_ID=4

class ChildActivity: BaseActivity(), MainMenuAdapter.Listener {


    private lateinit var presenter: CategoryContract.Presenter
    private var pageId: Int = 1
    val list = ArrayList<Category>()
    override fun onCreate(saveInstanceState: Bundle?){
        super.onCreate(saveInstanceState)
        supportActionBar?.title = resources.getString(R.string.category2)
        setContentView(R.layout.activity_main_menu)
        list.add(Category("Часто задаваемые вопросы", R.drawable.faq))
        list.add(Category("Ресурсы по воспитанию детей", R.drawable.second))
        list.add(Category("Поддержка экспертов", R.drawable.third))
        list.add(Category("Досуг для детей", R.drawable.fourth))
        list.add(Category("Список Экспертов", R.drawable.fifth))

        recyclerView.adapter = MainMenuAdapter(list, this)



    }
    override fun onItemSelectedAt(position: Int) {
        if(position == 4){
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