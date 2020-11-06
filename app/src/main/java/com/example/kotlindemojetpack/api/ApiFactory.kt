package com.example.kotlindemojetpack.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  create by pan yi on 2020/11/6
 *  desc : 网络工厂类
 */
object ApiFactory {
    private val client by lazy {
        OkHttpClient.Builder().apply {
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            connectionPool(ConnectionPool(8, 30, TimeUnit.SECONDS))
            addInterceptor(BaseInterceptor())
            addInterceptor(LogInterceptor())
        }.build()
    }

    fun createApiService(baseUrl:String): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}