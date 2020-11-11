package com.example.kotlindemojetpack.extension

import android.accounts.NetworkErrorException
import android.util.MalformedJsonException
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ExceptionUtil {
    class ApiResolveException(val result: Int, val msg: String) : Throwable(msg)

    fun catchException(e: Throwable): BaseErr {
        if (e is ApiResolveException) {
            return BaseErr(e.msg, e.result)
        }
        e.printStackTrace()
        var msg = ""
        when (e) {
            is HttpException ->
                msg = catchHttpException(e.code())
            is SocketTimeoutException ->
                msg = "网络请求超时"
            is UnknownHostException, is NetworkErrorException ->
                msg = "网络错误"
            is MalformedJsonException, is JsonSyntaxException ->
                msg = "数据解析错误"
            else ->{
                msg = "未知错误"
                return BaseErr(msg,0)
            }
        }
        return BaseErr(msg)
    }

    /**
     * 处理网络异常
     */
    private fun catchHttpException(errorCode: Int): String {
        if (errorCode in 200 until 300) return "请求成功"
        when (errorCode) {
            in 500..600 -> return "服务器错误"
            in 400 until 500 -> return "请求错误"
            else -> return "未知错误"
        }
    }

}