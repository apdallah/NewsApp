package com.apdallahy.newsapp.data.usecases

import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.repos.StocksRepositoryImpl
import kotlinx.coroutines.CoroutineScope

class GetStocksFromAsset(
    private val repo: StocksRepositoryImpl,
    ioScope: CoroutineScope
) : BaseUseCase<String?, HashMap<String?, ArrayList<Double?>?>?>(ioScope) {
    override suspend fun callApi(params: String?): Response<HashMap<String?, ArrayList<Double?>?>?> {
        return repo.getStocksNews()

    }


}