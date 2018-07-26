package com.example.virus.kotlininfamily.ui.main.section_for_child.specialist_article

import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistArticle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialistArticlePresenter(val view : SpecialistArticleContract.View?) :  SpecialistArticleContract.Presenter{


    override fun getSpecialistArticleById(id: Int) {
        if(isViewAttached()){
            view?.showProgress()
            StartApplication.service.getSpecialistArticle(id).enqueue(object: Callback<SpecialistArticle>{

                override fun onResponse(call: Call<SpecialistArticle>?, response: Response<SpecialistArticle>?) {
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

                 override fun onFailure(call: Call<SpecialistArticle>?, t: Throwable?) {
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