package com.apdallahy.newsapp.UI

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.models.StockModel
import com.apdallahy.newsapp.data.usecases.GetAllNewsUseCase
import com.apdallahy.newsapp.data.usecases.GetStocksFromAsset
import com.todayapps.netgrutask.data.common.BaseViewModel
import com.todayapps.netgrutask.data.models.NewsModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class NewsViewModel(
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val getStocksFromAsset: GetStocksFromAsset,

    application: Application
) :
    BaseViewModel(application) {
    private val stocksData = MutableLiveData<HashMap<String?, ArrayList<Double?>?>>()
    val randomStocksData = MutableLiveData<ArrayList<StockModel>>()
    val newsData = MutableLiveData<MutableList<NewsModel>>()
    val topNewsData = MutableLiveData<MutableList<NewsModel>>()

    init {
        loadStocksFromAsset()
    }

    fun loadNews() {
        showLoading()
        getAllNewsUseCase(null) { apiCallResult ->
            hideLoading()
            when (apiCallResult) {
                is Response.Error -> showError(apiCallResult.error?.errorMessage)
                is Response.Success -> {
                    submitTopNews(apiCallResult.data?.articles)
                    submitNews(apiCallResult.data?.articles)

                }
            }
        }
    }

    private fun submitTopNews(allNews: ArrayList<NewsModel>?) {
        allNews?.let {
            val top6=it.subList(0,6)
            if(top6.isNotEmpty()){
                topNewsData.postValue(top6)
            }
        }

    }
    private fun submitNews(allNews: ArrayList<NewsModel>?) {
        allNews?.let {
            val restNews=it.subList(6,it.size-1)
            if(restNews.isNotEmpty()){
                newsData.postValue(restNews)
            }
        }


    }

    fun loadStocksFromAsset() {
        showLoading()
        getStocksFromAsset(null) { response ->
            hideLoading()
            when (response) {
                is Response.Error -> showError(response.error?.errorMessage)
                is Response.Success -> {
                    stocksData.postValue(response.data)
                    startPeriodicEvent()
                }
            }
        }
    }

    fun startPeriodicEvent() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                loadRandomStocks()
            }

        }

    }

    fun loadRandomStocks() {
        val data = stocksData.value
        val randomStocks = arrayListOf<StockModel>()
        data?.keys?.forEach {
            val randomId = Random.nextInt(data[it]?.size ?: 0)
            randomStocks.add(StockModel(it, data[it]?.get(randomId)))
        }
        randomStocksData.postValue(randomStocks)
    }


}