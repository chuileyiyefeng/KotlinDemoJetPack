package com.example.kotlindemojetpack.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlindemojetpack.MyApplication

/**
 *  create by pan yi on 2020/11/12
 *  desc :
 */

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this.context).load(url).into(this)
}