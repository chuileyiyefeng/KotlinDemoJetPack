package com.example.kotlindemojetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemojetpack.MyApplication
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.ui.common.callback.RequestCallBack
import java.lang.Exception

/**
 *  create by pan yi on 2020/11/3
 *  desc : 基类fragment
 */
abstract class BaseFragment : Fragment(), RequestCallBack, LifecycleObserver {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(bindLayout(), container, false)
            loadingProgressBar = rootView?.findViewById(R.id.progress)
        }
        return rootView
    }


    private var isFirstLoad = true
    private var isInit = false

    private var loadingErrorView: View? = null
    private var loadingProgressBar: ProgressBar? = null
    private var rootView: View? = null
    abstract fun bindLayout(): Int
    protected fun getViewModel(clazz: Class<out BaseViewModel>?): BaseViewModel? {
        var model: BaseViewModel? = null
        if (clazz != null) {
            model = ViewModelProvider.AndroidViewModelFactory
                .getInstance(MyApplication())
                .create(clazz)
        }
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
    open fun initObserver() {}
    open fun lazyLoad() {}
    open fun reload() {}


    override fun startLoading() {
        loadingProgressBar?.visibility = View.VISIBLE
        loadingErrorView?.visibility = View.GONE
    }

    override fun loadSuccess() {
        loadingProgressBar?.visibility = View.GONE
        loadingErrorView?.visibility = View.GONE
    }

    override fun loadFail(msg: String?) {
        loadingProgressBar?.visibility = View.GONE
        if (loadingErrorView != null) {
            loadingErrorView?.visibility = View.VISIBLE
        } else {
            rootView?.let {
                val viewStub = it.findViewById(R.id.view_load_stub) as ViewStub
                loadingErrorView = viewStub.inflate()

            }
        }
    }

    protected fun showErrorMessage(msg: String?, block: View.() -> Unit) {
        loadingErrorView?.let { loadError ->
            val textView = loadError.findViewById<TextView>(R.id.tv_load_error_content)
            textView.text = msg
            val viewError = loadError.findViewById<View>(R.id.view_load_error)
            viewError.setOnClickListener {
                it.block()
            }
        }
    }
}