package com.apdallahy.newsapp.extentions

import com.apdallahy.newsapp.data.models.ErorrMessage
import com.apdallahy.newsapp.data.models.Response
import okhttp3.ResponseBody
import retrofit2.Call

fun <T> Call<T>.requestBlocking(): Response<T?> {
    try {
        val result = execute()

        when (result.isSuccessful) {
            true -> return Response.Success(result.body())
            false -> return Response.Error(getErrorMessage(result.errorBody()))
        }
    } catch (ex: Exception) {
        return Response.Error(getExcptionMessage(ex))
    }
}

private fun getErrorMessage(errorBody: ResponseBody?): ErorrMessage? {
    return ErorrMessage(errorBody.toString())
}

private fun getExcptionMessage(exception: Exception?): ErorrMessage? {
    return ErorrMessage(exception?.localizedMessage)
}