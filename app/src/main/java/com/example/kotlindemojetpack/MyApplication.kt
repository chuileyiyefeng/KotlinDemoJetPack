package com.example.kotlindemojetpack

import android.app.Application
import com.scwang.smart.refresh.header.MaterialHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

/**
 *  create by pan yi on 2020/11/5
 *  desc :
 */
class MyApplication : Application() {
    init {
        SmartRefreshLayout.setDefaultRefreshInitializer { _, layout ->
            layout.setEnableLoadMore(false)
            layout.setEnableLoadMoreWhenContentNotFull(false)
        }

        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setEnableHeaderTranslationContent(true)
            MaterialHeader(context).setColorSchemeResources(R.color.blue, R.color.blue, R.color.blue)
        }
    }
}