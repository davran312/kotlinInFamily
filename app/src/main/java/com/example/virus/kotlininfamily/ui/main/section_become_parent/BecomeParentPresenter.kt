package com.example.virus.kotlininfamily.ui.main.section_become_parent

import android.content.Context
import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.example.virus.kotlininfamily.utils.IStatusResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BecomeParentPresenter(val view:BecomeParentContract.View) :BecomeParentContract.Presenter
{


    override fun getMainMenuCategoryArticles(id: Int) {
        if(isViewAttached()){
            view?.showProgress()
            StartApplication.service.getMainMenuCategoryArticles(3).enqueue(object :Callback<List<Categories>>{
                override fun onFailure(call: Call<List<Categories>>?, t: Throwable?) {
                    if(isViewAttached()){
                        view!!.onError("Error")
                        view!!.hideProgress()
                    }
                    t?.printStackTrace()
                }

                override fun onResponse(call: Call<List<Categories>>?, response: Response<List<Categories>>?) {
                    if(isViewAttached()){
                        if(response!!.isSuccessful && response.body() != null){
                            view!!.onSuccess(response.body()!!)
                        }
                        else
                            view!!.onError("Error")

                        view!!.hideProgress()
                    }
                }


            })

        }
    }
    override fun checkStatus(context: Context) {
        val listOfAuthData:ArrayList<String>  =
                FileUtils.readCacheData(context, Const.USER_AUTH_INFORMATION) as ArrayList<String>
        val id: String ?=  listOfAuthData.get(2) as String
        val userId:Int? = FileUtils.readCacheData(context, Const.USER_ID)
        if(isViewAttached()){
            view?.showProgress()
            StartApplication.service.checkStatus(userId!!,id!!).enqueue(
                    object :Callback<DocumentStatus>{

                        override fun onResponse(call: Call<DocumentStatus>?, response: Response<DocumentStatus>?) {
                            if(response!!.isSuccessful && response != null) {
                                view.onSuccessStatus(response.body()!!)

                            }
                        }
                        override fun onFailure(call: Call<DocumentStatus>?, t: Throwable?) {
                            t?.printStackTrace()
                        }

                    }
            )
            view?.hideProgress()

        }
    }


    private fun isViewAttached(): Boolean = view != null
}