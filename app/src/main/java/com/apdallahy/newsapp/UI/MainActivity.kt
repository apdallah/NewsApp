package com.apdallahy.newsapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apdallahy.newsapp.R
import com.apdallahy.newsapp.UI.adapters.news.NewsAdapter
import com.apdallahy.newsapp.UI.adapters.stocks.StocksAdapter
import com.apdallahy.newsapp.data.common.DefaultDialogLoading
import com.apdallahy.newsapp.databinding.ActivityMainBinding
import com.bedaya.mortgage.base_components.Dialogs.IDialogLoading
import com.google.android.material.snackbar.Snackbar
import com.todayapps.netgrutask.extentions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val  viewModel: NewsViewModel by viewModels()

    protected var dialogLoading: IDialogLoading? = null
    lateinit var dataBinding: ActivityMainBinding
    lateinit var topNewsAdapter: NewsAdapter
    lateinit var stocksAdapter: StocksAdapter
    lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogLoading = DefaultDialogLoading(this)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initTopNewsAdapter()
        initStocksAdapter()
        initNewsAdapter()
        observe(viewModel.newsData) { data ->
            data?.let {
                newsAdapter.updateData(it)
            }

        }
        observe(viewModel.topNewsData) { data ->
            data?.let {
                topNewsAdapter.updateData(it)
            }

        }
        observe(viewModel.randomStocksData) {data->
            data?.let {
                stocksAdapter.updateData(it)
            }

        }
        observe(viewModel.error) {
            if (!it.isNullOrEmpty()) {
                Snackbar.make(dataBinding.mainView, it, Snackbar.LENGTH_LONG).show()
            }
        }
        observe(viewModel.isLoading) {
            if (it == true) {
                runOnUiThread {
                    dialogLoading?.showLoading()
                }
            } else {
                runOnUiThread {
                    dialogLoading?.hideLoading()
                }
            }
        }
        viewModel.loadNews()

    }

    private fun initTopNewsAdapter() {
        topNewsAdapter = NewsAdapter(NewsAdapter.TOP_NEWS_VIEW_TYPE)
        dataBinding.topNewsRv.adapter = topNewsAdapter
        dataBinding.topNewsRv.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
    }

    private fun initStocksAdapter() {
        stocksAdapter = StocksAdapter()
        dataBinding.stocksRv.adapter = stocksAdapter
        dataBinding.stocksRv.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
    }

    private fun initNewsAdapter() {
        newsAdapter = NewsAdapter(NewsAdapter.NEWS_VIEW_TYPE)
        dataBinding.newsRv.adapter = newsAdapter
        dataBinding.newsRv.layoutManager =
            GridLayoutManager(this@MainActivity, 1, RecyclerView.VERTICAL, false)
    }

}