package com.apdallahy.newsapp.data.repos

import com.apdallahy.newsapp.data.models.Response

interface StockRepository {
    suspend fun getStocksNews(): Response<HashMap<String?, ArrayList<Double?>?>?>

}