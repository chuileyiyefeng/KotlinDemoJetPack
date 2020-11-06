package com.example.kotlindemojetpack.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlindemojetpack.api.Repository
import com.example.kotlindemojetpack.base.BaseViewModel
import com.example.kotlindemojetpack.reponse.DiscoveryBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 *  create by pan yi on 2020/11/3
 *  desc : 主页发现
 */
class HomeFoundFragmentVm : BaseViewModel() {
    val listData = MutableLiveData<DiscoveryBean>()

    fun getData() {
        viewModelScope.launch {
            val discovery = Repository.getDiscovery()
        }
    }

}