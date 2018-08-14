package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization.LoginActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentPresenter
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentStatusActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_information.InformationActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_test.TestActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_main_menu.*

@Suppress("DEPRECATION")
class BecomeParentActivity: BaseActivity(),BecomeParentAdapter.Listener,BecomeParentContract.View {

    lateinit var list: List<Categories>
    lateinit var presenter: BecomeParentPresenter
    lateinit var adapter : BecomeParentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")

        init()

    }

    override fun onSuccessStatus(result: DocumentStatus) {
        checkStatus(result)
    }

    override fun onSuccess(result: List<Categories>) {
        initRecyclerView(result)
        list = result

    }

    private fun initRecyclerView(result:List<Categories>) {
        adapter = BecomeParentAdapter(result,this)
        recyclerView.adapter = adapter
    }

    fun init() {
        presenter = BecomeParentPresenter(this)
        presenter.getMainMenuCategoryArticles(3)


    }
    private fun checkStatus(response:DocumentStatus) {
        val intent =Intent(this, DocumentStatusActivity::class.java)
        if(response.status !=  0 && response.status != 5 && response.status != 4){
            intent.putExtra("serverStatus","Ваша заявка в данный момент в процессе проверки")
            intent.putExtra("buttonText","Попробовать позже")
        }

        else if (response.status == 5)
        {

            intent.putExtra("serverStatus","Ваша заявка принята, ожидайте с вами свяжутся наши" +
                    "специалисты")
            intent.putExtra("buttonText","Выйти")

        }
        else if (response.status == 4)
        {

            intent.putExtra("serverStatus","Ваша заявка не прошла проверку," +
                    "пожалуйста исправте свою заявку" +
                    "")
            intent.putExtra("buttonText","Переотправить")
            intent.putExtra("statusBody",response)


        }
        FileUtils.writeCacheData(this,"status",response.status)
        startActivity(intent)


    }


    override fun onItemSelectedAt(position: Int) {
        if( position == 1 && userInfoIsEmpty()){
            startActivity(Intent(this,LoginActivity::class.java))
        }
        else if( position == 1) {
            val status:Int? = FileUtils.readCacheData(this,Const.UDAPTE_APPLICATION_STATUS)
            if(status == 1 || status == 2){
                presenter.checkStatus(this)
            }else {
                startActivity(Intent(this, DocumentsActivity::class.java))
            }
        }
        else{
        if(position == 0){
            startActivity(Intent(this,InformationActivity::class.java))
        }

       if(position == 2)
       {
           startActivity(Intent(this,TestActivity::class.java))
       }
    }
    }


    private fun userInfoIsEmpty(): Boolean {
        val array: ArrayList<String>? = FileUtils.readCacheData(this, Const.USER_AUTH_INFORMATION)
        return array == null
    }
}