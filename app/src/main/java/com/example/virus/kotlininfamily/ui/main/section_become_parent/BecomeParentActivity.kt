package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.content.Intent
import android.os.Bundle
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization.LoginActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.InformationActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test.TestActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.fragment_category_first.*
import kotlinx.android.synthetic.main.item_shadow_button.view.*
import java.io.File

class BecomeParentActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_category_first)

        information.textView.text = getString(R.string.section_information)
        documents.textView.text = getString(R.string.section_documents)
        test.textView.text = FileUtils.fromHtml(getString(R.string.section_test))

        information.setOnClickListener{
            startActivity(Intent(this, InformationActivity::class.java))
        }
        test.setOnClickListener {
            startActivity(Intent(this,TestActivity::class.java))
        }
        documents.setOnClickListener{
            if(userInfoIsEmpty()){
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else
                startActivity(Intent(this,DocumentsActivity::class.java))
        }
    }

    private fun userInfoIsEmpty(): Boolean {
        val array :ArrayList<String>? = FileUtils.readCacheData(this,Const.USER_AUTH_INFORMATION)
        return array == null
    }
}