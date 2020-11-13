package com.example.kotlindemojetpack.utils

import androidx.lifecycle.MutableLiveData

/**
 *  create by pan yi on 2020/11/13
 *  desc :
 */
class LiveBus {
    companion object {
        val refreshData by lazy {
            MutableLiveData<Class<*>>()
        }
    }


}