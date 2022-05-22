package com.apdallahy.newsapp.data.repos

import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response

interface NewsRepository {
   suspend fun getAll(): Response<ArrayList<NewsModel>?>
   suspend fun getStocksNews(): Response<HashMap<String?,ArrayList<Double?>?>?>
 }