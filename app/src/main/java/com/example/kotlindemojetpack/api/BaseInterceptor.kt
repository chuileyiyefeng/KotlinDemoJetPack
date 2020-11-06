package com.example.kotlindemojetpack.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 *  create by pan yi on 2020/11/6
 *  desc : 拦截器
 */
class BaseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("token", "token")
        return chain.proceed(builder.build())
    }
}