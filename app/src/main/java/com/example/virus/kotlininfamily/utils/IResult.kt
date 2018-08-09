package com.example.virus.kotlininfamily.utils

import com.example.virus.kotlininfamily.ui.main.section_become_parent.section_for_documents.DocumentsActivity

interface IResult<T> {

    fun  onSuccess(result: T)
    fun onError(message: String?)
}