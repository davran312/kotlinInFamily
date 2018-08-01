package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.content.Context
import android.telephony.TelephonyManager
import android.widget.Toast
import com.example.virus.kotlininfamily.R
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.File
import java.util.*
import retrofit2.Callback
import retrofit2.Response

class DocumentPresenter(val view:DocumentContract.View?):DocumentContract.Presenter{


    override fun sendApplication(map: HashMap<Int, String>, context: Context) {
        val bodyBuilder = MultipartBody.Builder()
        fillApplication(map,bodyBuilder,context)
        bodyBuilder.setType(MultipartBody.FORM)
        if(isViewAttached()) {
            view?.showProgress()
            StartApplication.service.sendApplication(bodyBuilder.build()).
                    enqueue(object: Callback<ResponseBody>{
                        override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                            if(isViewAttached()){
                                if(response!!.isSuccessful && response.body() != null){
                                    view!!.onSuccess(response.body()!!)
                                }
                                else{
                                    view!!.onError("Error")
                                }
                                view.hideProgress()

                            }
                        }

                        override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                            if(isViewAttached()){
                                view!!.onError("Нет ответа от сервера")
                                view!!.hideProgress()
                            }
                            t?.printStackTrace()
                        }

                    })
        }
    }
    private fun fillApplication(map: HashMap<Int, String>, body: MultipartBody.Builder, context: Context){
        val authInfoList :ArrayList<String> = FileUtils.readCacheData(context, Const.USER_AUTH_INFORMATION)
        body.addFormDataPart("name",authInfoList.get(0))
        body.addFormDataPart("phone",authInfoList.get(2))
        body.addFormDataPart("email",authInfoList.get(1))
        body.addFormDataPart("device_id",authInfoList.get(2))
              val array: Array<Array<String>> = arrayOf(context.resources.getStringArray(R.array.server_document_titles))
          for(i in 0.. map.size-1){
              if(map.get(i).isNullOrEmpty())
                  Toast.makeText(context,""+i,Toast.LENGTH_SHORT).show()
              else {
                  var file = File(map.get(i))
                  body.addFormDataPart(array[0][i], file.name, RequestBody.create(MediaType.parse(
                          "multipart/form-data"), file))
              }
        }

    }
    private fun isViewAttached() :Boolean = view != null

}