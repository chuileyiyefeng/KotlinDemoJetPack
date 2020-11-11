package com.example.kotlindemojetpack.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import com.example.kotlindemojetpack.MyApplication

/**
 *  create by pan yi on 2020/11/11
 *  desc : 基类 fragment
 */
abstract class BaseFragment : Fragment(), LifecycleObserver {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(bindLayout(), container, false)

        }
        return rootView
    }


    private var isFirstLoad = true
    private var isInit = false

    private var rootView: View? = null
    private var viewModel: BaseViewModel? = null
    abstract fun bindLayout(): Int
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

    }

    open fun lazyLoad() {}
    open fun reload() {}
}
