package com.example.kotlindemojetpack.ui.fragment

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseActivity
import com.example.kotlindemojetpack.base.BaseFragment
import com.example.kotlindemojetpack.extension.setOnClickListener
import com.example.kotlindemojetpack.ui.adapter.SearchAdapter
import com.example.kotlindemojetpack.ui.viewmodel.SearchFragmentViewModel
import com.example.kotlindemojetpack.utils.llogE
import kotlinx.android.synthetic.main.fragment_search.*

/**
 *  create by pan yi on 2020/11/13
 *  desc : 搜索fragment
 */
class SearchFragment : BaseFragment() {

    private val viewModel =
        getViewModel(SearchFragmentViewModel::class.java) as SearchFragmentViewModel

    private var adapter: SearchAdapter? = null
    override fun bindLayout(): Int {
        return R.layout.fragment_search
    }

    override fun initView() {

        et_content.showSoftKeyboard()
        setOnClickListener(tv_cancel) {
            activity?.let {
                hideSoftKeyboard()
                removeFragment(it, this@SearchFragment)
            }
        }
        context?.let {
            adapter = SearchAdapter(it)
            rv_search_hint.layoutManager = LinearLayoutManager(it)
            rv_search_hint.adapter = adapter
        }
    }

    override fun lazyLoad() {
        viewModel.getHotSearch()
    }

    override fun initObserver() {
        viewModel.listData.observe(this, {
            adapter?.addItem(it)
        })
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return if (enter) {
            AnimationUtils.loadAnimation(activity, R.anim.anl_push_up_in)
        } else {
            AnimationUtils.loadAnimation(activity, R.anim.anl_push_up_out)
        }
    }

    /**
     * 隐藏软键盘
     */
    private fun hideSoftKeyboard() {
        try {
            activity?.currentFocus?.run {
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        } catch (e: Exception) {
            llogE(e.message ?: "")
        }
    }

    /**
     * 拉起软键盘
     */
    private fun View.showSoftKeyboard() {
        try {
            this.isFocusable = true
            this.isFocusableInTouchMode = true
            this.requestFocus()
            val manager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.showSoftInput(this, 0)
        } catch (e: Exception) {
            llogE(e.message ?: "")
        }
    }

    private fun removeFragment(activity: Activity, fragment: Fragment) {
        (activity as BaseActivity).supportFragmentManager.run {
            beginTransaction().remove(fragment).commitAllowingStateLoss()
            popBackStack()
        }
    }
}