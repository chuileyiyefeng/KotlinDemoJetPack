package com.example.kotlindemojetpack.api

import com.example.kotlindemojetpack.base.BaseResponse
import com.example.kotlindemojetpack.reponse.DiscoveryBean
import retrofit2.Call
import retrofit2.http.GET

/**
 *  create by pan yi on 2020/11/6
 *  desc : 网络接口
 */
interface ApiService {
    @GET("api/v7/index/tab/discovery")
   suspend fun getDiscovery(): DiscoveryBean

    /**
     * 搜索-热搜关键词
     */
    @GET("api/v3/queries/hot")
    suspend fun getHotSearch(): List<String>
}