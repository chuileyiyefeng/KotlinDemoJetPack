package com.example.kotlindemojetpack.ui.fragment

import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseFragment
import com.example.kotlindemojetpack.extension.loadImageUrl
import kotlinx.android.synthetic.main.fragment_pager.*

/**
 *  create by pan yi on 2020/11/12
 *  desc :
 */
class PagerFragment(val url: String) : BaseFragment() {
    override fun bindLayout(): Int {
        return R.layout.fragment_pager
    }

    override fun initView() {
        iv_banner.loadImageUrl(url)
    }
}