package com.example.kotlindemojetpack.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.*

/**
 *  create by pan yi on 2020/11/3
 *  desc :
 */
abstract class BaseAdapter<T>(private val context: Context, val needItemClick: Boolean = true) :
    RecyclerView.Adapter<BaseViewHolder>(), View.OnClickListener {
    private var list= ArrayList<T>()
    protected abstract fun bindLayout(): Int
    var currentType = 0
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        currentType = i
        return BaseViewHolder(LayoutInflater.from(context).inflate(bindLayout(), viewGroup, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        bindHolder(holder, position)
        if (needItemClick) {
            holder.itemView.tag = position
            holder.itemView.setOnClickListener(this)
        }
    }

    protected abstract fun bindHolder(holder: BaseViewHolder, position: Int)
    override fun getItemCount(): Int {
        return list.size
    }

    fun addItem(t: T) {
        list.add(t)
        notifyItemInserted(list.size)
    }

    fun addFirstItem(t: T) {
        list.add(0, t)
        notifyItemInserted(list.size)
    }

    fun addItem(collections: Collection<T>?) {
        list.addAll(collections!!)
        notifyDataSetChanged()
    }

    fun removePosition(position: Int) {
        list.removeAt(position)
        notifyDataSetChanged()
    }

    fun clearAllItem() {
        list.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return list[position]
    }

    private var itemClick: ItemClick? = null
    fun addItemClick(itemClick: ItemClick?) {
        this.itemClick = itemClick
    }

    override fun onClick(v: View) {
        val position = v.tag as Int
        if (itemClick != null) {
            itemClick!!.itemClick(position)
        }
    }

    interface ItemClick {
        fun itemClick(position: Int)
    }

}