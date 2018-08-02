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
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_documents.*
import okhttp3.ResponseBody
import java.io.File


class DocumentsActivity : BaseActivity(), DocumentAdapter.Listener, DocumentContract.View {



    private var adapter: DocumentAdapter? = null
    var map: HashMap<Int, String> = HashMap()
    private var selectedIndex: Int = -1
    private  var updateCode: Int = 0

    private lateinit var presenter: DocumentPresenter
    private var documentName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_documents)
        init(updateCode)
        checkStatus(updateCode)



    }

    private fun checkStatus(updateCode: Int?) {
        if(updateCode == 3)
        {
            Toast.makeText(this,"Ваша заявка рассматривается",Toast.LENGTH_SHORT).show()
            button_send_request.isClickable = false
        }
        else if (updateCode == 5)
        {
            Toast.makeText(this,"Ваша заявка успешно принята",Toast.LENGTH_SHORT).show()
            button_send_request.isClickable = false
        }

    }

    private fun init(code:Int) {
        initVariables()
        initAdapter()
        initListeners(code)
    }
    override fun onSuccessStatus(status: Int) {
        Toast.makeText(this,""+status,Toast.LENGTH_SHORT).show()
        updateCode = status
    }


    private fun initListeners(code:Int) {
       button_send_request.setOnClickListener {
           when(code){
               1-> updateApplication()
               3-> resetApplication()
               else -> sendApplication()
           }
       }
    }

    private fun resetApplication() {

    }

    private fun updateApplication() {
        presenter.updateApplication(map,this)
    }


    private fun initVariables() {
        presenter = DocumentPresenter(this)
        presenter.checkStatus(map,this)
        val temp: HashMap<Int, String>? = FileUtils.readCacheData(this, Const.CACHE_URI_DIRECTORY)
        if (temp != null)
            map = temp


    }

    private fun initAdapter() {
        adapter = DocumentAdapter(resources.getStringArray
        (com.example.virus.kotlininfamily.R.array.documents_list), map, this)
        recyclerViewOfDocuments.addItemDecoration(DividerItemDecoration(this))
        recyclerViewOfDocuments.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        recyclerViewOfDocuments.adapter = adapter
    }

    fun sendApplication() {

        val list: Array<out String>? = (resources.getStringArray(R.array.documents_list))
        if (map.size == 0)
            Toast.makeText(this, "Заявка пуста", Toast.LENGTH_LONG).show()
        else if (map.size < list!!.size)
            Toast.makeText(this, "Заполните недостающие поля", Toast.LENGTH_LONG).show()
        else{
            presenter.sendApplication(map, this,this)
        }

    }

    override fun onItemSelectedAt(index: Int, document: String) {
        this.selectedIndex = index
        this.documentName = document

        showDialog()
    }


    fun onGetDataFromDialog(imagePath: String) {
        if (imagePath != null) {
            if (selectedIndex != -1 && !map.containsKey(selectedIndex)) map.put(selectedIndex, imagePath)
            else if (map.containsKey(selectedIndex)) map[selectedIndex] = imagePath
            adapter?.setFilledIndex(selectedIndex, imagePath)
        }
    }

    override fun onSuccess(result: DocumentStatus) {
        FileUtils.writeCacheData(this,Const.USER_ID,result.id)
        FileUtils.writeCacheData(this,"model",result)
        Toast.makeText(this, "Заявка отправляется ", Toast.LENGTH_LONG).show()

    }

    private fun getId(body: String): String {
        return body.toString()


    }

    override fun onBackPressed() {
        FileUtils.writeCacheData(this, Const.CACHE_URI_DIRECTORY, map)
        super.onBackPressed()
    }


    fun showDialog() {

        val ft = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag(Const.TAG_FOR_SHOW_DIALOG_FRAGMENT)

        if (prev != null) {
            ft.remove(prev)
        }
        ft.addToBackStack(null)


        val newFragment = MyDialogFragment.newInstance(documentName!!, map[selectedIndex])
        newFragment!!.show(ft, Const.TAG_FOR_SHOW_DIALOG_FRAGMENT)

    }
}