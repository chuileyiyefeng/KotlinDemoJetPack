package com.example.kotlindemojetpack.ui.common.callback

/**
 *  create by pan yi on 2020/11/4
 *  desc : 网络请求回调
 */
interface RequestCallBack {
    fun startLoading()
    fun loadMore()
    fun refresh()
    fun loadSuccess()
    fun loadFail(msg: String?)
}