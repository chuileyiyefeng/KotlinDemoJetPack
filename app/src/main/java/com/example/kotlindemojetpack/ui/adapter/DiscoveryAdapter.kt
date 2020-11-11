package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder
import com.example.kotlindemojetpack.reponse.Item
import com.example.kotlindemojetpack.reponse.ItemX
import com.example.kotlindemojetpack.view.layoutmanager.BannerLayoutManager

/**
 *  create by pan yi on 2020/11/6
 *  desc : 发现
 */
class DiscoveryAdapter(private val context: Context) :
    BaseAdapter<Item>(context, needItemClick = false) {

    override fun getItemViewType(position: Int): Int {
        val bean = getItem(position)
        return when (bean.type) {
            "horizontalScrollCard" -> {
                0
            }
            else -> {
                1
            }
        }
    }

    override fun bindLayout(): Int {
        val bean = getItem(currentBindPosition)
        return when (bean.type) {
            "horizontalScrollCard" -> {
                R.layout.adapter_discovery
            }
            "specialSquareCardCollection" -> {
                R.layout.adapter_empty
            }
            "columnCardList" -> {
                R.layout.adapter_empty
            }
            "banner" -> {
                R.layout.adapter_empty
            }
            "banner3" -> {
                R.layout.adapter_empty
            }
            "videoSmallCard" -> {
                R.layout.adapter_empty
            }
            "briefCard" -> {
                R.layout.adapter_empty
            }
            "followCard" -> {
                R.layout.adapter_empty
            }
            "informationCard" -> {
                R.layout.adapter_empty
            }
            "ugcSelectedCardCollection" -> {
                R.layout.adapter_empty
            }
            "autoPlayVideoAd" -> {
                R.layout.adapter_empty
            }
            else -> {
                R.layout.adapter_empty
            }
        }
    }

    private val attachRvPosition by lazy {
        ArrayList<Int>()
    }

    override fun bindHolder(holder: BaseViewHolder, position: Int) {
        val bean = getItem(position)
        when (getItemViewType(position)) {
            0 -> {
                val rv = holder.getView<RecyclerView>(R.id.rv)
                var adapter: BaseAdapter<ItemX>? = null
                rv.visibility = View.GONE
                when (bean.type) {
                    "horizontalScrollCard" -> {
                        rv.layoutManager = BannerLayoutManager(context)
                        adapter = BannerAdapter(context)
                        adapter.addItem(bean.data.itemList)
                        rv.visibility = View.VISIBLE
                        if (!attachRvPosition.contains(position)) {
                            LinearSnapHelper().attachToRecyclerView(rv)
                            attachRvPosition.add(position)
                        }
                    }
                }
                adapter?.let {
                    rv.adapter = it
                }
            }
            else -> {
            }
        }
    }
}