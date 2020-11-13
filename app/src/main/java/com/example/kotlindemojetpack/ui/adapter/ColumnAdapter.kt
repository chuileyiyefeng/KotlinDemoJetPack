package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder
import com.example.kotlindemojetpack.extension.loadUrl
import com.example.kotlindemojetpack.reponse.ItemX

/**
 *  create by pan yi on 2020/11/12
 *  desc :
 */
class ColumnAdapter(context: Context) : BaseAdapter<ItemX>(context) {
    override fun bindLayout(): Int {
        return R.layout.adapter_column_item
    }

    override fun bindHolder(holder: BaseViewHolder, position: Int) {
        val bean = getItem(position)
        val iv = holder.getView(R.id.iv_column) as ImageView
        val tv = holder.getView(R.id.tv_column_title) as TextView
        iv.loadUrl(bean.data.image)
        tv.text = bean.data.title
    }
}