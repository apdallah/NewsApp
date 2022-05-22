package com.apdallahy.newsapp.data.repos

import android.app.Application
import com.apdallahy.newsapp.NewsApplication
import com.apdallahy.newsapp.data.common.FileHelper
import com.apdallahy.newsapp.data.models.NewsResponse
import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.models.StockModel
import com.apdallahy.newsapp.data.remote.NewsService
import com.apdallahy.newsapp.extentions.requestBlocking
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
    private val application: Application,
    private val newsService: NewsService
) : NewsRepository {
    override suspend fun getAll(): Response<NewsResponse?> {
        return withContext(Dispatchers.IO) {
            newsService.getAllNews().requestBlocking()
        }

    }

    override suspend fun getStocksNews(): Response<HashMap<String?, ArrayList<Double?>?>?> {

        val jsonString =
            FileHelper.getJsonDataFromAsset(application.applicationContext, "stocks.json")
        val gson = Gson()
        val listStockType = object : TypeToken<List<StockModel>>() {}.type

        val data: List<StockModel>? = gson.fromJson(jsonString, listStockType)
        val map = HashMap<String?, ArrayList<Double?>?>()
        data?.forEach {
            if (map.containsKey(it.stock)) {
                val currentMapArr = map[it.stock]
                currentMapArr?.add(it.price)
                map[it.stock] = currentMapArr
            } else {
                map[it.stock] = arrayListOf(it.price)
            }
        }
        return Response.Success(map)

    }


}