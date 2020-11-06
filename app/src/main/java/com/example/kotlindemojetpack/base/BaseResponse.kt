package com.example.kotlindemojetpack.base

/**
 *  create by pan yi on 2020/11/6
 *  desc : 返回值基类
 */
open class BaseResponse<T>(var data: T)

fun <T> BaseResponse<T>.convert(): T {
    return data
}