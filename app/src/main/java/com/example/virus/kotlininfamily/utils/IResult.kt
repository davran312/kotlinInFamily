package com.example.virus.kotlininfamily.utils

interface IResult<T> {

    fun onSuccess(result: T)
    fun onError(message: String?)
}