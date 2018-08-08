package com.example.virus.kotlininfamily.utils.error_log

import android.content.ContentValues.TAG
import android.util.Log
import com.example.virus.kotlininfamily.utils.Const
import com.example.virus.kotlininfamily.utils.FileUtils
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId



/**
 * Created by admin on 8/7/18.
 */
class MyFirebaseInstanceIDService : FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        super.onTokenRefresh()
        val refreshedToken = FirebaseInstanceId.getInstance().token
        FileUtils.writeCacheData(this, Const.REFRESHED_TOKEN_FOR_FIREBASE,refreshedToken)
        Log.d(TAG, "Refreshed token: " + refreshedToken!!)


    }
}