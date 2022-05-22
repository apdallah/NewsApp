package com.apdallahy.newsapp.data.repos

import com.apdallahy.newsapp.data.models.NewsResponse
import com.todayapps.netgrutask.data.models.NewsModel
import com.apdallahy.newsapp.data.models.Response

interface NewsRepository {
   suspend fun getAll(): Response<NewsResponse?>
 }