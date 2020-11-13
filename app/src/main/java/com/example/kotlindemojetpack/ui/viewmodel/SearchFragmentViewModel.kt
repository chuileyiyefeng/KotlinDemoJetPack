package com.example.kotlindemojetpack.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.kotlindemojetpack.api.Repository
import com.example.kotlindemojetpack.base.BaseViewModel
import com.example.kotlindemojetpack.extension.requestScope

/**
 *  create by pan yi on 2020/11/13
 *  desc :
 */
class SearchFragmentViewModel : BaseViewModel() {
    val listData = MutableLiveData<List<String>>()
    fun getHotSearch() {
        requestScope({
            val list = Repository.getHotSearch()
            listData.value = list
        })
    }
}