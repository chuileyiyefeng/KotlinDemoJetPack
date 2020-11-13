package com.example.kotlindemojetpack.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseActivity
import com.example.kotlindemojetpack.base.BaseFragment
import com.example.kotlindemojetpack.base.BaseListFragment
import com.example.kotlindemojetpack.extension.setOnClickListener
import com.example.kotlindemojetpack.ui.adapter.ViewPagerAdapter
import com.example.kotlindemojetpack.utils.LiveBus
import com.example.kotlindemojetpack.utils.llogE
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  create by pan yi on 2020/11/3
 *  desc : 首页
 */
class HomeFragment : BaseFragment() {
    private val fragmentList = ArrayList<BaseListFragment>()
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
        LiveBus.refreshData.observe(this, {
            if (it == javaClass) {
                fragmentList[viewpager.currentItem].autoRefresh()
            }
        })
        setOnClickListener(iv_search) {
            (activity as BaseActivity).supportFragmentManager.beginTransaction()
                .replace(android.R.id.content, SearchFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }
}