package com.example.kotlindemojetpack

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kotlindemojetpack.base.BaseActivity
import com.example.kotlindemojetpack.base.BaseListFragment
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
        if (view == lastView && isSelect) {
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
        imageView.setImageResource(
            when (view) {
                home -> {
                    showFragment(isSelect,homeFragment)
                    if (isSelect) {
                        R.mipmap.btn_home_page_selected
                    } else {
                        R.mipmap.btn_home_page_normal
                    }
                }
                society -> {
                    showFragment(isSelect,societyFragment)
                    if (isSelect) {
                        R.mipmap.btn_community_selected
                    } else {
                        R.mipmap.btn_community_normal
                    }
                }
                notify -> {
                    showFragment(isSelect,notifyFragment)
                    if (isSelect) {
                        R.mipmap.btn_notification_selected
                    } else {
                        R.mipmap.btn_notification_normal
                    }
                }
                mine -> {
                    showFragment(isSelect,mineFragment)
                    if (isSelect) {
                        R.mipmap.btn_mine_selected
                    } else {
                        R.mipmap.btn_mine_normal
                    }
                }
                else -> {
                    0
                }
            }
        )
        lastView?.let {
            if (isSelect) {
                changePage(it, false)
            }
        }
        if (isSelect) {
            lastView = view
        }
    }

    private fun showFragment(isSelect: Boolean, fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (isSelect) {
            if (!fragment.isAdded) {
                ft.add(R.id.fl_fragment, fragment)
            } else {
                ft.show(fragment)
            }
        } else {
            if (fragment.isAdded) {
                ft.hide(fragment)
            }
        }
        ft.commit()
    }
}