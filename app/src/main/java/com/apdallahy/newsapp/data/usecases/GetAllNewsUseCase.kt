package com.apdallahy.newsapp.data.usecases

import com.apdallahy.newsapp.data.models.NewsResponse
import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.repos.NewsRepositoryImpl
import com.apdallahy.newsapp.data.usecases.BaseUseCase
import kotlinx.coroutines.CoroutineScope

class GetAllNewsUseCase(
    private val repositoryImpl: NewsRepositoryImpl,
    ioScope: CoroutineScope
) : BaseUseCase<String?, NewsResponse?>(ioScope) {
    override suspend fun callApi(params: String?): Response<NewsResponse?> {
         return repositoryImpl.getAll()
    }




}