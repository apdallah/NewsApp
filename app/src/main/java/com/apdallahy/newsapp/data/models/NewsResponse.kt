package com.apdallahy.newsapp.data.models

import com.todayapps.netgrutask.data.models.NewsModel

data class NewsResponse(
    val status: String,
    val totalResult: Int,
    val articles: ArrayList<NewsModel>?=null
) {
}