package com.apdallahy.newsapp

import android.service.autofill.Validators.not
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.apdallahy.newsapp.UI.NewsViewModel
import com.apdallahy.newsapp.data.models.Response
import com.apdallahy.newsapp.data.usecases.GetAllNewsUseCase
import com.apdallahy.newsapp.data.usecases.GetStocksFromAsset
import com.todayapps.netgrutask.data.models.NewsModel
import org.junit.Before
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.Rule

import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

class NewsViewModelTest {
    @get:Rule
    val exceptionRule = ExpectedException.none()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: NewsViewModel
    val dataSet = arrayListOf<NewsModel>()

    @Before
    fun setup() {
        val getAllNewsUseCase: GetAllNewsUseCase = mock()
        val getStocksFromAsset: GetStocksFromAsset = mock()
        val application: NewsApplication = mock()
        viewModel = NewsViewModel(getAllNewsUseCase, getStocksFromAsset, application)
        dataSet.addAll(
            arrayListOf<NewsModel>(
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1"),
                NewsModel(id = "1")
            )
        )
    }

    @Test
    fun getTopNewsDataTest() {

        viewModel.submitTopNews(dataSet)
        assert(viewModel.topNewsData.value?.size == 6)

    }

    @Test
    fun getRecentNewsDataTest() {

        viewModel.submitNews(dataSet)
        assert(viewModel.newsData.value?.size == dataSet.size - 6)

    }

    @Test
    fun randomStockChangesTest() {
        val stockDataSet = HashMap<String?, ArrayList<Double?>?>().apply {
            put("TSLA", arrayListOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0))
            put("GLG", arrayListOf(8.0, 5.0, 7.0, 40.0, 90.0, 62.0))
            put("META", arrayListOf(86.0, 15.0, 78.0, 410.0, 190.0, 622.0))
        }
        viewModel.stocksData.value = stockDataSet
        viewModel.loadRandomStocks()
        val firstRandomisedStocksData = viewModel.randomStocksData.value
        viewModel.loadRandomStocks()
        val secoundRandomisedStockData = viewModel.randomStocksData.value
        Assert.assertNotEquals(firstRandomisedStocksData, secoundRandomisedStockData)

    }

}