package com.apdallahy.newsapp.data.usecases

import com.apdallahy.newsapp.data.models.Response
import kotlinx.coroutines.*

abstract class BaseUseCase<in P, out R>(private val ioScope: CoroutineScope) {
    abstract suspend fun callApi(params: P?=null): Response<R>

    operator fun invoke(params: P, onFetched: (response: Response<R?>) -> Unit) {
        ioScope.launch {
            val result = async { callApi(params) }

            withContext(Dispatchers.Main) {
                onFetched(result.await())
            }

        }

    }
}