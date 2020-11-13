package com.example.kotlindemojetpack.ui.fragment

import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseListFragment
import com.example.kotlindemojetpack.extension.setOnClickListener
import com.example.kotlindemojetpack.ui.adapter.DiscoveryAdapter
import com.example.kotlindemojetpack.ui.viewmodel.HomeFoundFragmentVm
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_list.*

/**
 *  create by pan yi on 2020/11/3
 *  desc : 首页发现
 */
class HomeFoundFragment : BaseListFragment() {
    private val viewModel by lazy {
        getViewModel(HomeFoundFragmentVm::class.java) as HomeFoundFragmentVm
    }
    private var adapter: DiscoveryAdapter? = null

    override fun bindLayout(): Int {
        return R.layout.fragment_home_found
    }

    override fun initView() {
        super.initView()
        context?.let {
            adapter = DiscoveryAdapter(it)
            setAdapter(adapter)
            refreshLayout.setOnRefreshListener {
                refresh()
            }
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.listData.observe(this, {
            loadData {
                adapter?.run {
                    addItem(it.itemList)
                }
            }
        })
    }


    override fun startLoading() {
        super.startLoading()
        viewModel.getData()
    }

    override fun refresh() {
        super.refresh()
        viewModel.getData()
    }

}