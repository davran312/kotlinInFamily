package com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents

import android.content.Context
import com.example.virus.kotlininfamily.models.DocumentStatus
import com.example.virus.kotlininfamily.utils.IProgressBar
import com.example.virus.kotlininfamily.utils.IResult
import com.example.virus.kotlininfamily.utils.IStatusResult

interface DocumentContract {
    interface View:IProgressBar ,IResult<DocumentStatus>,IStatusResult<Int>

    interface Presenter{
        fun sendApplication(map:HashMap<Int,String>,context: Context,activity: DocumentsActivity)
        fun updateApplication(map:HashMap<Int,String>,context:Context)
        fun checkStatus(map:HashMap<Int,String>,context:Context)

    }
}