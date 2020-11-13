package com.example.kotlindemojetpack.ui.adapter

import android.content.Context
import com.example.kotlindemojetpack.R
import com.example.kotlindemojetpack.base.BaseAdapter
import com.example.kotlindemojetpack.base.BaseViewHolder

/**
 *  create by pan yi on 2020/11/13
 *  desc :
 */
class SearchAdapter(context: Context) : BaseAdapter<String>(context) {
    override fun bindLayout(): Int {
        return R.layout.adapter_search
    }

    override fun bindHolder(holder: BaseViewHolder, position: Int) {
        holder.setText(R.id.tv_text, getItem(position))
    }
}