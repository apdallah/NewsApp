package com.apdallahy.newsapp.data.models

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
     data class Error(val error: ErorrMessage?=null) : Response<Nothing>()
}