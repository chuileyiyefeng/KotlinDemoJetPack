package com.example.kotlindemojetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemojetpack.MyApplication
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.reponse.Item
import com.example.kotlindemojetpack.ui.common.callback.RequestCallBack
import kotlinx.android.synthetic.main.view_list.*

/**
 *  create by pan yi on 2020/11/3
 *  desc : 基类 列表 fragment
 */
abstract class BaseListFragment : Fragment(), RequestCallBack, LifecycleObserver {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_home_found, container, false)
            loadingProgressBar = rootView?.findViewById(R.id.progress)
        }
        return rootView
    }


    private var isFirstLoad = true
    private var isInit = false

    private var loadingErrorView: View? = null
    private var loadingProgressBar: ProgressBar? = null
    private var rootView: View? = null
    private var viewModel: BaseViewModel? = null
    private var isRefresh = false
    protected fun getViewModel(clazz: Class<out BaseViewModel>?): BaseViewModel? {
        var model: BaseViewModel? = null
        if (clazz != null) {
            model = ViewModelProvider.AndroidViewModelFactory
                .getInstance(MyApplication())
                .create(clazz)
        }
        viewModel = model
        return model
    }

    override fun onResume() {
        super.onResume()
        if (!isInit) {
            initView()
            initObserver()
            isInit = true
        }
        if (isFirstLoad) {
            lazyLoad()
            isFirstLoad = false
        } else {
            reload()
        }
    }

    abstract fun initView()
    open fun initObserver() {
        startLoading()
        viewModel?.errorMessageData?.observe(this, {
            loadFail(it)
        })
    }

    open fun lazyLoad() {}
    open fun reload() {}


    override fun startLoading() {
        isRefresh = true
        if (isEmptyReConnect) {
            loadingProgressBar?.visibility = View.VISIBLE
        }
        loadingErrorView?.visibility = View.GONE
    }

    override fun loadSuccess() {
        refreshLayout.finishLoadMore()
        refreshLayout.finishRefresh()
        loadingProgressBar?.visibility = View.GONE
        loadingErrorView?.visibility = View.GONE
        isEmptyReConnect = false
    }

    // item为0并且加载错误 是否点击重连
    var isEmptyReConnect = false

    override fun loadFail(msg: String?) {
        refreshLayout.finishLoadMore()
        refreshLayout.finishRefresh()
        loadingProgressBar?.visibility = View.GONE

        showErrorMessage(msg) {
            startLoading()
        }
        // 如果item数量为0的时候显示错误的控件
        val itemCount = adapter?.itemCount ?: 0
        if (itemCount == 0) {
            isEmptyReConnect = true
            if (loadingErrorView != null) {
                loadingErrorView?.visibility = View.VISIBLE
            } else {
                rootView?.let {
                    val viewStub = it.findViewById(R.id.view_load_stub) as ViewStub
                    loadingErrorView = viewStub.inflate()
                }
            }
        }else{
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
    }


    private fun showErrorMessage(msg: String?, block: View.() -> Unit) {

        loadingErrorView?.let { loadError ->
            val textView = loadError.findViewById<TextView>(R.id.tv_load_error_content)
            textView.text = msg
            val viewError = loadError.findViewById<View>(R.id.view_load_error)
            viewError.setOnClickListener {
                it.block()
            }
        }
    }

    override fun refresh() {
        isRefresh=true
    }

    override fun loadMore() {

    }

    private var adapter: BaseAdapter<*>? = null

    open fun setAdapter(adapter: BaseAdapter<*>?) {
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
        this.adapter = adapter
    }
}