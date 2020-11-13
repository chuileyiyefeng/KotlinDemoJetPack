package com.example.kotlindemojetpack.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlindemojetpack.MyApplication
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.utils.GlideRoundTransform

/**
 *  create by pan yi on 2020/11/12
 *  desc :
 */

fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

// 加载圆角图片
fun ImageView.loadUrlRound(
    url: String?,
    resId: Int = R.color.blackAlpha95,
    radius: Int = 4
) {
    Glide.with(context).load(url).placeholder(resId)
        .transform(GlideRoundTransform(radius))
        .into(this)
}
