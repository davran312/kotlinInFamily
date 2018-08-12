package com.example.virus.kotlininfamily

import android.app.Application
import com.example.virus.kotlininfamily.data.ForumService
import com.example.virus.kotlininfamily.data.Network
import com.example.virus.kotlininfamily.utils.ConnectionsManager
import com.example.virus.kotlininfamily.utils.error_log.FileLog
import com.facebook.drawee.backends.pipeline.Fresco
import java.io.File

class StartApplication : Application(){
   private val BASE_URL = "http://165.227.147.84:5678/api/"

    companion object {
        @Volatile
        lateinit var INSTANCE : StartApplication
        lateinit var service: ForumService
    }
    override fun onCreate(){
        super.onCreate()
        INSTANCE = this
        service = Network.initService(BASE_URL)
        ConnectionsManager.getInstance()
        Fresco.initialize(this)
    }
    fun getFilesDirFixed(): File {
        for(a in 0..9){
            val path = INSTANCE.applicationContext.getFilesDir()
            if(path != null){
                return path
            }
        }
        try{
            val info = applicationContext.applicationInfo
            val path = File(info.dataDir,"files")
            path.mkdirs()
            return path
        }
        catch(e: Exception){
            FileLog.e(e)
        }
        return File("/data/test/files")
    }
}