package com.example.kotlindemojetpack.ui.fragment

import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseFragment
import com.example.kotlindemojetpack.base.BaseViewModel
import com.example.kotlindemojetpack.ui.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  create by pan yi on 2020/11/3
 *  desc : 首页
 */
class HomeFragment : BaseFragment() {
    private val fragmentList = ArrayList<BaseFragment>()
    override fun bindLayout(): Int {
        return R.layout.fragment_home
    }


    override fun initView() {
        tab.addTab(tab.newTab())
        tab.addTab(tab.newTab())
        tab.addTab(tab.newTab())
        fragmentList.add(HomeFoundFragment())
        fragmentList.add(HomeRecommendFragment())
        fragmentList.add(HomeDailyFragment())
        val viewPagerAdapter = ViewPagerAdapter(childFragmentManager, fragmentList)
        viewpager.adapter = viewPagerAdapter
        tab.setupWithViewPager(viewpager)
        tab.getTabAt(0)?.text = getString(R.string.found)
        tab.getTabAt(1)?.text = getString(R.string.recommend)
        tab.getTabAt(2)?.text = getString(R.string.daily)
    }



}