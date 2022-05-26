package com.apdallahy.newsapp.data.usecases

import com.apdallahy.newsapp.data.models.NewsResponse
import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.repos.NewsRepositoryImpl
import com.apdallahy.newsapp.data.usecases.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllNewsUseCase @Inject constructor(
       val repositoryImpl: NewsRepositoryImpl,
        val ioScope: CoroutineScope
) : BaseUseCase<String?, NewsResponse?>(ioScope) {
    override suspend fun callApi(params: String?): Response<NewsResponse?> {
         return repositoryImpl.getAll()
    }




}