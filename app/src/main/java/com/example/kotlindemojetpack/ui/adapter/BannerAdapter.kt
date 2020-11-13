package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Space
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder
import com.example.kotlindemojetpack.extension.loadUrlRound
import com.example.kotlindemojetpack.reponse.ItemX

/**
 *  create by pan yi on 2020/11/9
 *  desc : banner类型
 */
class BannerAdapter(context: Context) : BaseAdapter<ItemX>(context) {
    override fun bindLayout(): Int {
        return R.layout.adapter_discovery_banner
    }

    override fun bindHolder(holder: BaseViewHolder, position: Int) {
        val bean = getItem(position)
        val iv = holder.getView<ImageView>(R.id.iv_banner)
        iv.loadUrlRound(bean.data.image)
    }

}