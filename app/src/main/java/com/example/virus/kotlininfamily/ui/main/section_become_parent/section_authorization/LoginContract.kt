package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_authorization

import android.content.Context
import android.widget.Toast
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
import com.example.virus.kotlininfamily.utils.IStatusResult

/**
 * Created by admin on 8/9/18.
 */
interface LoginContract {
    interface View:IProgressBar{
        fun onError(message : String?)

    }
    interface Presenter{
        fun sendToken(context: Context, activity: LoginActivity)
    }
}