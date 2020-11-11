package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder
import com.example.kotlindemojetpack.reponse.ItemX

/**
 *  create by pan yi on 2020/11/9
 *  desc : banner类型
 */
class BannerAdapter(private val context: Context) : BaseAdapter<ItemX>(context) {
    override fun bindLayout(): Int {
        return R.layout.adapter_discovery_banner
    }

    override fun bindHolder(holder: BaseViewHolder, position: Int) {
        val bean = getItem(position)
        val iv = holder.getView<ImageView>(R.id.iv_banner)
        Glide.with(context).load(bean.data.image).into(iv)
    }

}