package com.example.kotlindemojetpack.api

import com.example.kotlindemojetpack.utils.llogD
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
        llogD("request Url ${request.url()} ")
        llogD("  response body:$content")
        return chain.proceed(builder.build())
    }
}