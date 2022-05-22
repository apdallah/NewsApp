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
    val newsData = MutableLiveData<ArrayList<NewsModel>>()
    val topNewsData = MutableLiveData<ArrayList<NewsModel>>()

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
                    topNewsData.postValue(apiCallResult.data?.subList(0, 6) as ArrayList<NewsModel>)
                    newsData.postValue(
                        apiCallResult.data.subList(
                            6,
                            apiCallResult.data.size
                        ) as ArrayList<NewsModel>
                    )
                }
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
            val randomId = Random(data[it]?.size ?: 0)
            randomStocks.add(StockModel(it, data[it]?.get(randomId.nextInt())))
        }
        randomStocksData.postValue(randomStocks)
    }


}