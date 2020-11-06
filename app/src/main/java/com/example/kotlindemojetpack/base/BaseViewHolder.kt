package com.example.kotlindemojetpack.base

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 *  create by pan yi on 2020/11/3
 *  desc :
 */
class BaseViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mViews by lazy {
        SparseArray<View>()
    }

    fun setText(id: Int, text: String?) {
        val tv: TextView = itemView.findViewById(id)
        tv.text = text
    }

    fun <T : View> getView(viewId: Int): T {
        var view = mViews[viewId]
        if (view == null) {
            view = itemView.findViewById(viewId)
            mViews.put(viewId, view)
        }
        return view as T
    }


}