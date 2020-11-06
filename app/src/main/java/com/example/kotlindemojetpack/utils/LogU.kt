package com.example.kotlindemojetpack.utils

import android.util.Log

/**
 *  create by pan yi on 2020/11/6
 *  desc : log工具类
 */
private const val isLog = true
fun logE(tag: String, msg: String?) {
    if (isLog) {
        Log.e(tag, msg.toString())
    }
}