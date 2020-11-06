package com.example.kotlindemojetpack.api

/**
 *  create by pan yi on 2020/11/6
 *  desc : 网络配置
 */
object NetConfig {
     val BASE_URL = "http://baobab.kaiyanapp.com/"
    val apiService by lazy { ApiFactory.createApiService(BASE_URL) }
}