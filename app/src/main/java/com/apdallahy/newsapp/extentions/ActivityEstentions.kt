package com.todayapps.netgrutask.extentions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ComputableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

fun AppCompatActivity.showLoading() {

}

fun AppCompatActivity.hideLoading() {

}

fun AppCompatActivity.showError(errorMessage: String) {

}


fun <T : Any, L : LiveData<T>> AppCompatActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))