package com.apdallahy.newsapp.data.remote

import com.todayapps.netgrutask.data.models.NewsModel
import retrofit2.Call
import retrofit2.http.GET
interface NewsService {
    @GET("everything/cnn.json")
    fun getAllNews():Call<ArrayList<NewsModel>>
}