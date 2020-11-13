package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import android.graphics.Typeface
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder
import com.example.kotlindemojetpack.extension.loadUrl
import com.example.kotlindemojetpack.reponse.Item
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
            "briefCard" -> {
                1
            }
            "textCard" -> {
                2
            }
            "columnCardList" -> {
                3
            }
            else -> {
                4
            }
        }
    }

    override fun bindLayout(): Int {
        return when (currentType) {
            0 -> {
                R.layout.adapter_discovery
            }
            1 -> {
                R.layout.adapter_category
            }
            2 -> {
                R.layout.adapter_text
            }
            3 -> {
                R.layout.adapter_column
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
                val rv = holder.getView<RecyclerView>(R.id.rv_list)
                if (!attachRvPosition.contains(position)) {
                    LinearSnapHelper().attachToRecyclerView(rv)
                    attachRvPosition.add(position)
                }
                val adapter = BannerAdapter(context)
                rv.layoutManager = BannerLayoutManager(context)
                rv.adapter = adapter
                adapter.addItem(bean.data.itemList)
            }
            1 -> {
                val iv = holder.getView(R.id.iv_category) as ImageView
                val tvTitle = holder.getView(R.id.tv_title) as TextView
                val tvContent = holder.getView(R.id.tv_content) as TextView
                iv.loadUrl(bean.data.icon)
                tvTitle.text = bean.data.title
                tvContent.text = bean.data.description
            }
            2 -> {
                val tvTextCard = holder.getView(R.id.tv_textCard) as TextView
                tvTextCard.setTextColor(
                    context.resources.getColor(
                        when (bean.data.type) {
                            "footer2" -> {
                                R.color.blue
                            }
                            else -> {
                                R.color.textColor
                            }
                        }, null
                    )
                )
                tvTextCard.text = bean.data.text
            }
            3 -> {
                val tvColumn = holder.getView(R.id.tv_column) as TextView
                tvColumn.text = bean.data.header.title
                when (bean.data.header.font) {
                    "bold" -> {
                        tvColumn.typeface = Typeface.DEFAULT_BOLD
                    }
                }
                val rv = holder.getView<RecyclerView>(R.id.rv_column)
                rv.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                val adapter = ColumnAdapter(context)
                rv.adapter = adapter
                adapter.addItem(bean.data.itemList)
            }
        }
    }
}