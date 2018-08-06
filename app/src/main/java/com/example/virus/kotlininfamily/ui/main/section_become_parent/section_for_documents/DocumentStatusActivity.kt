package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.virus.infamily.mvp.ui.ui.documents.DocumentAdapter
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.BecomeParentPresenter
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_document_status.*

class DocumentStatusActivity : BaseActivity(), DocumentContract.View {
    override fun onSuccess(result: DocumentStatus) {
    }

    lateinit var presenter: DocumentPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_status)

        text_view.text = intent.getStringExtra("serverStatus")
        button_retry.text = intent.getStringExtra("buttonText")
        val status:Int = FileUtils.readCacheData(this,"status")
        presenter = DocumentPresenter(this)
        button_retry.setOnClickListener {
            when(button_retry.text){
                "Выйти"->finish()
                "Переотправить"->{startActivity(Intent(this,DocumentsActivity::class.java))
                finish()
                }
                "Попробовать позже"->finish()
            }

        }
        if(status == 4 ) {
            var result: DocumentStatus? = intent.getSerializableExtra("statusBody") as DocumentStatus?
            if(result!!.status ==4 ){
                FileUtils.writeCacheData(this,Const.UDAPTE_APPLICATION_STATUS,result.status)
                checkDocumentCorrectness(result)

            }


    }

    }
    private fun checkDocumentCorrectness( result: DocumentStatus) {
        val map:HashMap<Int,String>? = FileUtils.readCacheData(this, Const.CACHE_URI_DIRECTORY)
        val list:ArrayList<Boolean> = getList(result)

        for(i in 0 until list.size){
            if(list.get(i)) {
                map!!.remove(i)
            }
        }
        FileUtils.writeCacheData(this, Const.CACHE_URI_DIRECTORY, map)

    }
    private fun getList(result: DocumentStatus): ArrayList<Boolean> {
        Log.d("result",result.toString())
        val list:ArrayList<Boolean> = ArrayList()
        list.add(result.family_correct)
        list.add(result.income_correct)
        list.add(result.residence_correct)
        list.add(result.criminal_correct)
        list.add(result.health_corrent)
        list.add(result.job_correct)
        list.add(result.res_correct)
        list.add(result.bio_correct)
        return list
    }



    override fun onSuccessStatus(result: DocumentStatus) {
        Toast.makeText(this,""+result.status,Toast.LENGTH_SHORT).show()
        if(result.status == 5){
            text_view.text = "Ваша заявка принята"
            button_retry.text ="Выйти"
        }

    }
}