package com.apdallahy.newsapp.data.repos

import com.apdallahy.newsapp.data.models.NewsResponse
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.remote.NewsService
import com.apdallahy.newsapp.extentions.requestBlocking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRepositoryImpl(
     private val newsService: NewsService
) : NewsRepository {
    override suspend fun getAll(): Response<NewsResponse?> {
        return withContext(Dispatchers.IO) {
            newsService.getAllNews().requestBlocking()
        }

    }




}