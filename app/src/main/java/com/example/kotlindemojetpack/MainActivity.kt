package com.example.kotlindemojetpack

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.kotlindemojetpack.base.BaseActivity
import com.example.kotlindemojetpack.extension.setOnClickListener
import com.example.kotlindemojetpack.ui.fragment.HomeFragment
import com.example.kotlindemojetpack.ui.fragment.MineFragment
import com.example.kotlindemojetpack.ui.fragment.NotifyFragment
import com.example.kotlindemojetpack.ui.fragment.SocietyFragment
import kotlinx.android.synthetic.main.bottom_navigation_view.*

class MainActivity : BaseActivity(R.layout.activity_main) {
    var lastView: View? = null
    private val homeFragment by lazy {
        HomeFragment()
    }
    private val societyFragment by lazy {
        SocietyFragment()
    }
    private val notifyFragment by lazy {
        NotifyFragment()
    }
    private val mineFragment by lazy {
        MineFragment()
    }

    override fun initView() {
        changePage(home)
        changePage(society, false)
        changePage(notify, false)
        changePage(mine, false)
        setOnClickListener(home, society, iv_add, notify, mine) {
            when (this) {
                iv_add -> {

                }
                else -> {
                    changePage(this)
                }
            }
        }
    }

    private fun changePage(view: View, isSelect: Boolean = true) {
        if (view == lastView&&isSelect) {
            return
        }
        val parent = view as ViewGroup
        val imageView = parent.getChildAt(0) as ImageView
        val textView = parent.getChildAt(1) as TextView
        textView.setTextColor(
            resources.getColor(
                when (isSelect) {
                    true -> {
                        R.color.black
                    }
                    else -> {
                        R.color.grayDark
                    }
                }, null
            )
        )
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        imageView.setImageResource(
            when (view) {
                home -> {
                    if (isSelect) {
                        if (!homeFragment.isAdded) {
                            ft.add(R.id.fl_fragment, homeFragment)
                        } else {
                            ft.show(homeFragment)
                        }
                        R.mipmap.btn_home_page_selected
                    } else {
                        if (homeFragment.isAdded) {
                            ft.hide(homeFragment)
                        }
                        R.mipmap.btn_home_page_normal
                    }
                }
                society -> {
                    if (isSelect) {
                        if (!societyFragment.isAdded) {
                            ft.add(R.id.fl_fragment, societyFragment)
                        } else {
                            ft.show(societyFragment)
                        }
                        R.mipmap.btn_community_selected
                    } else {
                        if (societyFragment.isAdded) {
                            ft.hide(societyFragment)
                        }
                        R.mipmap.btn_community_normal
                    }
                }
                notify -> {
                    if (isSelect) {
                        if (!notifyFragment.isAdded) {
                            ft.add(R.id.fl_fragment, notifyFragment)
                        } else {
                            ft.show(notifyFragment)
                        }
                        R.mipmap.btn_notification_selected
                    } else {
                        if (notifyFragment.isAdded) {
                            ft.hide(notifyFragment)
                        }
                        R.mipmap.btn_notification_normal
                    }
                }
                mine -> {
                    if (isSelect) {
                        if (!mineFragment.isAdded) {
                            ft.add(R.id.fl_fragment, mineFragment)
                        } else {
                            ft.show(mineFragment)
                        }
                        R.mipmap.btn_mine_selected
                    } else {
                        if (mineFragment.isAdded) {
                            ft.hide(mineFragment)
                        }
                        R.mipmap.btn_mine_normal
                    }
                }
                else -> {
                    0
                }
            }
        )
        ft.commit()
        lastView?.let {
            if (isSelect) {
                changePage(it, false)
            }
        }
        if (isSelect) {
            lastView = view
        }
    }
}