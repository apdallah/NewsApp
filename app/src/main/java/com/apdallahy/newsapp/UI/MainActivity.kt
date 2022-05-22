package com.apdallahy.newsapp.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.apdallahy.newsapp.R
import com.apdallahy.newsapp.data.common.DefaultDialogLoading
import com.apdallahy.newsapp.databinding.ActivityMainBinding
import com.bedaya.mortgage.base_components.Dialogs.IDialogLoading
import com.google.android.material.snackbar.Snackbar
import com.todayapps.netgrutask.extentions.hideLoading
import com.todayapps.netgrutask.extentions.observe
import com.todayapps.netgrutask.extentions.showLoading
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    val viewModel: NewsViewModel by viewModel()
    protected var dialogLoading: IDialogLoading? = null
    lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogLoading = DefaultDialogLoading(this)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observe(viewModel.newsData) {

        }
        observe(viewModel.topNewsData) {

        }
        observe(viewModel.randomStocksData) {

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

}