package com.example.kotlindemojetpack.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.utils.StatusBarUtil

/**
 *  create by pan yi on 2020/11/3
 *  desc : 基类Activity
 */
abstract class BaseActivity(private val resourceId: Int) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(resourceId)
        StatusBarUtil.setStatusBarColor(this, R.color.white)
        StatusBarUtil.setStatusTextDark(this, true)
        initView()
    }

    abstract fun initView()
}