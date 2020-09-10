package com.nhapps.zennote.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

    fun ViewModel.getViewModelScope(coroutineScope: CoroutineScope?): CoroutineScope {
        if (coroutineScope == null){
            return CoroutineScope(Dispatchers.IO)
        }else {
            return coroutineScope
        }
    }