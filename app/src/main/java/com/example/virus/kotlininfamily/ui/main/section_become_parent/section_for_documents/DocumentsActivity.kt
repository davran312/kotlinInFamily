package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.widget.Toast
import com.example.virus.infamily.mvp.ui.ui.documents.DocumentAdapter
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.models.TokenInfo
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_documents.*


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
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")
        init()

    }



    private fun init() {
        initVariables()
        initAdapter()
        initListeners()
    }
    override fun onSuccessStatus(response:DocumentStatus) {
        FileUtils.writeCacheData(this,Const.UDAPTE_APPLICATION_STATUS,response.status)
    }


    private fun initListeners() {
       button_send_request.setOnClickListener {
           val updateCode :Int?= FileUtils.readCacheData(this,Const.UDAPTE_APPLICATION_STATUS)
           when(updateCode){
               1-> updateApplication()
               2->updateApplication()
               3-> updateApplication()
               4->updateApplication()
               else -> sendApplication()
           }
       }
    }

    private fun updateApplication() {
        presenter.updateApplication(map,this,this)
        FileUtils.writeCacheData(this,Const.CACHE_URI_DIRECTORY,map)
    }


    private fun initVariables() {
        presenter = DocumentPresenter(this)
        if(FileUtils.readCacheData(this,Const.USER_ID) as Int? != null){
        val temp: HashMap<Int, String>? = FileUtils.readCacheData(this, Const.CACHE_URI_DIRECTORY)
            map = temp!!


        }
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
//        if (map.size == 0)
//            Toast.makeText(this, "Заявка пуста", Toast.LENGTH_LONG).show()
//        else if (map.size < list!!.size)
//            Toast.makeText(this, "Заполните недостающие поля", Toast.LENGTH_LONG).show()
//        else{
            FileUtils.writeCacheData(this, Const.CACHE_URI_DIRECTORY, map)

            presenter.sendApplication(map, this,this)

//        }

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

    private fun checkDocumentCorrectness( result: DocumentStatus) {
        val list:ArrayList<Boolean> = getList(result)
        for(i in 0 until list.size){
            if(list.get(i))
                map.remove(i)
        }
        FileUtils.writeCacheData(this, Const.CACHE_URI_DIRECTORY, map)

    }
    private fun getList(result: DocumentStatus): ArrayList<Boolean> {
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