package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.ui.main.BaseActivity
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
        val status :Int?= FileUtils.readCacheData(this,"status")
        Log.d("_______status",""+status)
        if(status == 4){
            val result = intent.getSerializableExtra("statusBody") as DocumentStatus
            if(result!!.status ==4 ) {
                checkDocumentCorrectness(result)
                FileUtils.writeCacheData(this, Const.UDAPTE_APPLICATION_STATUS, result.status)

            }
        }


    }
     fun checkDocumentCorrectness( result: DocumentStatus) {
        val map:HashMap<Int,String>? = FileUtils.readCacheData(this, Const.CACHE_URI_DIRECTORY)
        val list:ArrayList<Boolean> = getList(result)
        Log.d("thisMytag", result.toString())
        for(i in 0 until list.size){
            if(!list.get(i)) {
                map!!.remove(i)
            }

        }
        FileUtils.writeCacheData(this, Const.CACHE_URI_DIRECTORY, map)

    }
    public fun getList(result: DocumentStatus): ArrayList<Boolean> {
        Log.d("result",result.toString())
        val list:ArrayList<Boolean> = ArrayList()
        list.add(result.family_correct)
        list.add(result.income_correct)
        list.add(result.residence_correct)
        list.add(result.criminal_correct)
        list.add(result.health_correct)
        list.add(result.job_correct)
        return list
    }



    override fun onSuccessStatus(result: DocumentStatus) {

        }

    }


