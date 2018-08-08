package com.example.virus.kotlininfamily.data

import com.example.virus.kotlininfamily.models.*
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
    @GET("documents/{id}/")
    fun checkStatus(@Path("id") id: Int, @Header("DEVICE") deviceId:String): Call<DocumentStatus>

    @POST("devices/")
    fun sendToken(@Body file: RequestBody): Call<TokenInfo>
}
