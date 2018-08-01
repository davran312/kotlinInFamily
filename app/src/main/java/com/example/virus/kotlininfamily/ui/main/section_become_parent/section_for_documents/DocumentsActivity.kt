package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.example.virus.infamily.mvp.ui.ui.documents.DocumentAdapter
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_documents.*
import okhttp3.ResponseBody

class DocumentsActivity : BaseActivity(), DocumentAdapter.Listener,DocumentContract.View {



    private var adapter: DocumentAdapter? = null
    var map: HashMap<Int, String> = HashMap()
    private var selectedIndex: Int = -1
    private lateinit var presenter: DocumentPresenter
    private var documentName: String? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)
        initVariables()
        initAdapter()

        button_send_request.setOnClickListener{
            sendApplication()
        }


    }


    private fun initVariables() {
        presenter = DocumentPresenter(this)
        val temp:HashMap<Int,String>? =FileUtils.readCacheData(this,Const.CACHE_URI_DIRECTORY)
        if(temp != null)
            map = temp


    }

    private fun initAdapter() {
        adapter = DocumentAdapter(resources.getStringArray
        (com.example.virus.kotlininfamily.R.array.documents_list),map,this)
        recyclerViewOfDocuments.addItemDecoration( DividerItemDecoration(this))
        recyclerViewOfDocuments.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerViewOfDocuments.adapter = adapter
    }

    fun sendApplication(){
        if(map.size == 0)
            Toast.makeText(this,"Заявка пуста",Toast.LENGTH_LONG).show()

        else if (map.size < 8)
            Toast.makeText(this,"Заполните недостающие поля",Toast.LENGTH_LONG).show()
        else
            presenter.sendApplication(map,this)

    }

    override fun onItemSelectedAt(index: Int, document: String) {
        this.selectedIndex = index
        this.documentName = document

        showDialog()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_CANCELED && resultCode == RESULT_OK && requestCode == 1 && data != null) {
            val imagePath = data.getStringExtra("path")
            if (selectedIndex != -1 && !map.containsKey(selectedIndex)) map.put(selectedIndex, imagePath)
            else if (map.containsKey(selectedIndex)) map[selectedIndex] = imagePath
            adapter?.setFilledIndex(selectedIndex, imagePath)
        }

    }

    fun onGetData(imagePath : String){
        if (imagePath != null) {
            if (selectedIndex != -1 && !map.containsKey(selectedIndex)) map.put(selectedIndex, imagePath)
            else if (map.containsKey(selectedIndex)) map[selectedIndex] = imagePath
            adapter?.setFilledIndex(selectedIndex, imagePath)
        }
    }

    override fun onSuccess(result: ResponseBody) {
        Toast.makeText(this,"Заявка отправляется",Toast.LENGTH_LONG).show()

    }
    override fun onBackPressed() {
        FileUtils.writeCacheData(this,Const.CACHE_URI_DIRECTORY,map)
        super.onBackPressed()
    }



    fun showDialog() {

        // DialogFragment.show() will take care of adding the fragment
        // in a transaction.  We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")

        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)


        val newFragment = MyDialogFragment.newInstance(documentName!!, map[selectedIndex])

        newFragment!!.show(ft, "dialog")

    }
}