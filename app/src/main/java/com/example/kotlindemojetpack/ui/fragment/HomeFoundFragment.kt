package com.example.kotlindemojetpack.ui.fragment

import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseFragment
import com.example.kotlindemojetpack.extension.setOnClickListener
import com.example.kotlindemojetpack.ui.viewmodel.HomeFoundFragmentVm
import kotlinx.android.synthetic.main.fragment_home_found.*

/**
 *  create by pan yi on 2020/11/3
 *  desc : 首页发现
 */
class HomeFoundFragment : BaseFragment() {
    private val viewModel by lazy {
        getViewModel(HomeFoundFragmentVm::class.java) as HomeFoundFragmentVm
    }


    override fun bindLayout(): Int {
        return R.layout.fragment_home_found
    }

    override fun initView() {
        startLoading()
    }

    override fun initObserver() {
        viewModel.listData.observe(this, {

        })
    }


    override fun startLoading() {
        super.startLoading()
        viewModel.getData()
    }

    override fun loadSuccess() {
        super.loadSuccess()
    }

    override fun loadFail(msg: String?) {
        super.loadFail(msg)
        showErrorMessage(msg) {
            startLoading()
        }
    }
}