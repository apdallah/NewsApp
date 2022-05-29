package com.todayapps.netgrutask.data.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    val error = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    fun showLoading() {
        isLoading.postValue(true)
    }

    fun hideLoading() {
        isLoading.postValue(false)

    }

    fun showError(errorMesg: String?) {
        error.postValue(errorMesg)
    }
}