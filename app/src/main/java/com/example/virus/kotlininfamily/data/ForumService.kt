package com.example.virus.kotlininfamily.data

import com.example.virus.kotlininfamily.models.Categories
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.models.SpecialistArticle
import com.example.virus.kotlininfamily.models.SpecialistList
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ForumService {
    @GET("categories/{id}")
    fun getCategoriesList(@Path("id") id: Int): Call<List<Categories>>

    @POST("documents/")
    fun sendApplication(@Body file: RequestBody):Call<DocumentStatus>

    @GET("people/")
    fun getSpecialistList():Call<List<SpecialistList>>

    @GET("sections/{id}")
    fun getMainMenuCategoryArticles(@Path("id" )id:Int): Call <List<Categories>>
    @GET("people/{id}")
    fun getSpecialistArticle(@Path("id") id:Int): Call<SpecialistArticle>
    @PATCH("documents/{id}/")
    fun updateDocumentStatus(@Body file:RequestBody, @Path("id") id: Int, @Header("DEVICE") deviceId:String): Call<DocumentStatus>
    @PATCH("documents/{id}/")
    fun checkStatus(@Body file:RequestBody, @Path("id") id: Int, @Header("DEVICE") deviceId:String): Call<DocumentStatus>
}
