package com.example.virus.kotlininfamily.ui.main.section_for_child.categories

import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryPresenter(val view : CategoryContract.View?) :  CategoryContract.Presenter{


    override fun getCategoriesById(pageId:Int){
        if(isViewAttached()){
            view?.showProgress()
            StartApplication.service.getCategoriesList(pageId).enqueue(object: Callback<List<Categories>>{
                override fun onResponse(call: Call<List<Categories>>?, response: Response<List<Categories>>?) {
                    if(isViewAttached()){
                        if(response!!.isSuccessful && response.body() != null){
                            view!!.onSuccess(response.body()!!)
                        }
                        else{
                            view!!.onError("error")
                        }
                        view.hideProgress()
                    }
                }

                 override fun onFailure(call: Call<List<Categories>>?, t: Throwable?) {
                     if(isViewAttached()){
                         view!!.onError("error")
                         view.hideProgress()
                     }
                     t?.printStackTrace()
                }
            })
        }

    }
    private fun isViewAttached(): Boolean = view != null
}