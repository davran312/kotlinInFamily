@file:Suppress("DEPRECATION")
package com.example.virus.kotlininfamily.ui.main

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.MenuItem
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.utils.error_log.FileLog

open class BaseActivity : AppCompatActivity(){
    private var progressBar: ProgressDialog? = null

    override fun setContentView(layoutResId:Int){
        super.setContentView(layoutResId)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.left_arrow)

    }
    open fun showProgress(){
        this.runOnUiThread{
            progressBar = null
            if(progressBar == null && !isFinishing){
                progressBar = ProgressDialog(this)
                progressBar?.setMessage(getString(R.string.loading))
                progressBar?.setCanceledOnTouchOutside(false)
                progressBar?.show()
            }
        }
    }
    open fun hideProgress(){
        this.runOnUiThread{
            if(progressBar != null && progressBar?.isShowing!!){
                progressBar!!.dismiss()
                progressBar = null
            }
        }
    }
  open fun onError(message:String?){
      FileLog.showError(this,message)
  }
    override fun onOptionsItemSelected(item: MenuItem):Boolean{
        if(item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
    override fun onKeyDown(keyCode:Int,event: KeyEvent?):Boolean{
        if(keyCode == KeyEvent.KEYCODE_BACK){
            onBackPressed()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

     override fun onDestroy() {
        super.onDestroy()
         hideProgress()
    }

}