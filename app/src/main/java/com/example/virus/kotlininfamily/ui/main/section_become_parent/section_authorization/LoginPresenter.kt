package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization

import android.content.Context
import android.util.Log
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.TokenInfo
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentContract
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by admin on 8/9/18.
 */
class LoginPresenter(val view: LoginContract.View?) : LoginContract.Presenter {
    var token:String = String()
    override fun sendToken(context: Context, activity: LoginActivity) {
        var bodyBuilderForToken = MultipartBody.Builder()
        bodyBuilderForToken = fillTokenRequest(bodyBuilderForToken, context)
        bodyBuilderForToken.setType(MultipartBody.FORM)

        StartApplication.service.sendToken(bodyBuilderForToken.build()).enqueue(object : Callback<TokenInfo> {
            override fun onFailure(call: Call<TokenInfo>?, t: Throwable?) {
                t!!.printStackTrace()
            }

            override fun onResponse(call: Call<TokenInfo>?, response: Response<TokenInfo>?) {

                if (response!!.isSuccessful && response.body() != null) {
                    Log.d("________token", response.message())
                } else {
                    Log.d("________token","Токен использутеся")
                }
                view!!.hideProgress()

            }

        })

    }
    private fun fillTokenRequest(bodyBuilderForToken: MultipartBody.Builder, context: Context): MultipartBody.Builder {
        val authInfoList: ArrayList<String>? = FileUtils.readCacheData(context, Const.USER_AUTH_INFORMATION)
        var tempToken:String? = FileUtils.readCacheData(context, Const.REFRESHED_TOKEN_FOR_FIREBASE)
        if(tempToken != null){
            token =FileUtils.readCacheData(context, Const.REFRESHED_TOKEN_FOR_FIREBASE)
        }
        val name = authInfoList!![0]
        val deviceId = authInfoList[2]

        bodyBuilderForToken.addFormDataPart("name",name)
        bodyBuilderForToken.addFormDataPart("registration_id",token)
        bodyBuilderForToken.addFormDataPart("device_id", deviceId)
        bodyBuilderForToken.addFormDataPart("type", "android")
        bodyBuilderForToken.addFormDataPart("active", "true")


        return bodyBuilderForToken


    }
}
