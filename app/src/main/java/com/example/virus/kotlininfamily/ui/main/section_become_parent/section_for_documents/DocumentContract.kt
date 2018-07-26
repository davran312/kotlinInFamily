package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.content.Context
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
import okhttp3.ResponseBody

interface DocumentContract {
    interface View:IProgressBar ,IResult<ResponseBody>{}

    interface Presenter{
        fun sendApplication(map:HashMap<Int,String>,context: Context)

    }
}