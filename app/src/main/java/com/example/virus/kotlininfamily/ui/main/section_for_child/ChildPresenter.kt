package com.example.virus.kotlininfamily.ui.main.section_become_parent

import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChildPresenter(val view:ChildContract.View) :ChildContract.Presenter{

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
                        if(!response!!.isSuccessful && response.body()!!.isNotEmpty()){
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
    private fun isViewAttached(): Boolean = view != null
}