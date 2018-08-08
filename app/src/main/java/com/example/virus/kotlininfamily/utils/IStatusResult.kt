package com.example.virus.kotlininfamily.utils

import com.example.virus.kotlininfamily.models.TokenInfo

interface IStatusResult<T> {
    fun onSuccessStatus(result:T)
}