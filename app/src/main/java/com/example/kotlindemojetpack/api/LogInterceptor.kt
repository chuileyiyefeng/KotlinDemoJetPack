package com.example.kotlindemojetpack.api

import android.util.Log
import com.example.kotlindemojetpack.utils.logE
import okhttp3.Interceptor
import okhttp3.Response

/**
 *  create by pan yi on 2020/11/6
 *  desc : 日志拦截器
 */
class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val response = chain.proceed(chain.request())
        val content = response.body()?.string()
        val request = chain.request()
        logE("LogInterceptor", "request Url ${request.url()} ")
        logE("LogInterceptor", "  response body:$content")
        return chain.proceed(builder.build())
    }
}