package com.example.virus.kotlininfamily.ui.main.section_for_child.specialists


import com.example.virus.kotlininfamily.StartApplication
import com.example.virus.kotlininfamily.models.SpecialistList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpecialistPresenter(val view : SpecialistContract.View?) : SpecialistContract.Presenter {


    override fun getSpecialistList(){
        if(isViewAttached()){
            view?.showProgress()
            StartApplication.service.getSpecialistList().enqueue(object: Callback<List<SpecialistList>>{
                override fun onResponse(call: Call<List<SpecialistList>>?, response: Response<List<SpecialistList>>?) {
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

                 override fun onFailure(call: Call<List<SpecialistList>>?, t: Throwable?) {
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