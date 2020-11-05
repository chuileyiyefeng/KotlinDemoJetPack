package com.example.kotlindemojetpack.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlindemojetpack.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *  create by pan yi on 2020/11/3
 *  desc : 主页发现
 */
class HomeFoundFragmentVm : BaseViewModel() {
    val listData = MutableLiveData<String>()

    fun getData() {
        viewModelScope.launch {
            delay(1000)
            listData.value = "哈哈"
        }
    }
}