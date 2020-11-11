package com.example.kotlindemojetpack.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *  create by pan yi on 2020/11/3
 *  desc :
 */
open class BaseViewModel :ViewModel() {
    val errorMessageData = MutableLiveData<String>()
}