package com.example.kotlindemojetpack.extension

import android.content.Context
import android.view.View

/**
 *  create by pan yi on 2020/11/3
 *  desc : View相关的全局方法
 */

fun setOnClickListener(vararg views: View, block: View.() -> Unit) {
    val onClickListener = View.OnClickListener { it.block() }
    views.forEach {
        it.setOnClickListener(onClickListener)
    }
}
 fun View.dip2px(value: Int): Int {
    val scale: Float = resources.displayMetrics.density
    return (value * scale + 0.5f).toInt()
}
