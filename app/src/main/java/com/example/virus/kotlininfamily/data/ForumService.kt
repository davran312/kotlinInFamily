package com.example.virus.kotlininfamily.data

import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.models.SpecialistList
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ForumService {
    @GET("categories/{id}")
    fun getCategoriesList(@Path("id") id: Int): Call<List<Categories>>

    @POST("documents/")
    fun sendApplication(@Body file: RequestBody):Call<ResponseBody>

    @GET("people/")
    fun getSpecialistList():Call<List<SpecialistList>>

    @GET("sections/{id}")
    fun getMainMenuCategoryArticles(@Path("id" )id:Int): Call <List<Categories>>
    @GET("people/{id}")
    fun getSpecialistArticle(@Path("id") id:Int): Call<SpecialistArticle>
}
