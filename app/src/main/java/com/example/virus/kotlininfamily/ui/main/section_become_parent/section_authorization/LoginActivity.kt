package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import android.widget.EditText
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.ui.main.BaseActivity
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity :BaseActivity(),LoginContract.View {
    override fun onError(message: String?) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()

    }

    lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.title = Html.fromHtml("<font color=\"#ffffff\">" + getString(R.string.app_name) + "</font>")

        presenter = LoginPresenter(this)
        btn_auth.setOnClickListener {
            if (checkInputFieldValues()) {

                finish()
            }
        }
    }

    private fun checkInputFieldValues(): Boolean {
        val name = edit_name.text.toString()
        val mail = edit_mail.text.toString()
        val phone = edit_phone.text.toString()

        if (name.length<3 || name.isNullOrEmpty()) {
            edit_name.text.clear()
            edit_name.hint = "Введите полное имя"
            return false
        }
        if (mail.isEmpty()) {
            edit_mail.text.clear()
            edit_mail.setHintTextColor(resources.getColor(R.color.red))
            return false
        }
        if(!mail.isEmpty() && mail[0] == '@'){
            edit_mail.text.clear()
            edit_mail.setHintTextColor(resources.getColor(R.color.red))
            return false
        }
        if (phone.length < 10 || phone.isNullOrEmpty()) {
            edit_phone.text.clear()
            edit_phone.setHintTextColor(resources.getColor(R.color.red))
            return false
        }
        else{
            saveAuthFieldToCache(name,mail,phone)
            presenter.sendToken(this,this)
        return true
        }
    }

    private fun saveAuthFieldToCache(name: String, email: String, phone: String) {
        val authFields: ArrayList<String> = ArrayList()
        authFields.add(name)
        authFields.add(email)
        authFields.add(phone)

            FileUtils.writeCacheData(this, Const.USER_AUTH_INFORMATION,authFields)
        Toast.makeText(this,"Данные сохранены",Toast.LENGTH_SHORT).show()
        startActivity(Intent(this,DocumentsActivity::class.java))

    }
}