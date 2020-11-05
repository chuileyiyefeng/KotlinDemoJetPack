package com.example.kotlindemojetpack.extension

import android.view.View

/**
 *  create by pan yi on 2020/11/3
 *  desc : View的全局方法
 */

fun setOnClickListener(vararg views: View, block: View.() -> Unit) {
    val onClickListener = View.OnClickListener { it.block() }
    views.forEach {
        it.setOnClickListener(onClickListener)
    }
}

