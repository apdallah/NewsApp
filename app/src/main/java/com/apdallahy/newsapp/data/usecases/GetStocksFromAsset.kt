package com.apdallahy.newsapp.data.usecases

import com.apdallahy.newsapp.NewsApplication
import com.apdallahy.newsapp.data.common.FileHelper
import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.models.StockModel
import com.apdallahy.newsapp.data.repos.NewsRepositoryImpl
import com.apdallahy.newsapp.data.usecases.BaseUseCase
import kotlinx.coroutines.CoroutineScope

class GetStocksFromAsset(
    private val repositoryImpl: NewsRepositoryImpl,
    ioScope: CoroutineScope
) : BaseUseCase<String?, HashMap<String?, ArrayList<Double?>?>?>(ioScope) {
    override suspend fun callApi(params: String?): Response<HashMap<String?, ArrayList<Double?>?>?> {
        return repositoryImpl.getStocksNews()

    }


}