package com.todayapps.netgrutask.extentions

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> AndroidViewModel.emitValToStateFlow(emitVals: T, statFlow: MutableStateFlow<T>) {
    viewModelScope.launch {
        statFlow.emit(emitVals)
    }
}