package com.example.kotlindemojetpack.view.layoutmanager

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import kotlin.math.abs

/**
 *  create by pan yi on 2020/11/9
 *  desc :
 */
class BannerLayoutManager(context: Context?) : LinearLayoutManager(context) {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: Recycler, state: RecyclerView.State) {
        if (itemCount == 0) {
            removeAndRecycleAllViews(recycler)
            return
        }
        detachAndScrapAttachedViews(recycler)
        parentRight = width - paddingRight
        centerX = parentRight / 2
        lastPos = itemCount
        var left = firstLeft
        for (i in firstPos until lastPos) {
            val child = recycler.getViewForPosition(i)
            addView(child)
            measureChildWithMargins(child, 0, 0)
            val height = getDecoratedMeasuredHeight(child)
            val width = getDecoratedMeasuredWidth(child)
            if (i == 0) {
                left = (parentRight - width) / 2
            }
            layoutDecorated(child, left, 0, left + width, height)
            left += width
            if (left > parentRight) {
                lastPos = i
                break
            }
        }
        scaleView()
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    private var firstPos = 0
    private var lastPos = 0
    private var parentRight = 0
    private var centerX = 0
    private var firstLeft = 0
    private var scrollX = 0
    override fun scrollHorizontallyBy(dxx: Int, recycler: Recycler, state: RecyclerView.State): Int {
        var dx = dxx
        if (itemCount == 0) {
            return 0
        }
        if (dx > 0) {
            val lastView = getChildAt(childCount - 1)
            lastView?.let {
                val width = getDecoratedMeasuredWidth(it)
                val right = parentRight - (parentRight - width) / 2
                if (getPosition(it) == itemCount - 1) {
                    if (getDecoratedRight(it) - dx < right) {
                        dx = getDecoratedRight(it) - right
                    }
                }
            }
        } else if (scrollX + dx < 0) {
            dx = -scrollX
        }
        offsetChildrenHorizontal(-dx)
        dx = fillViews(dx, recycler)
        scrollX += dx
        return dx
    }

    private fun fillViews(dx: Int, recycler: Recycler): Int {
//        先回收后布局
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            child?.let {
                if (dx > 0) {
                    if (getDecoratedRight(it) + dx < paddingLeft) {
                        removeAndRecycleView(it, recycler)
                        firstPos++
                    }
                } else if (dx < 0) {
                    if (getDecoratedLeft(it) + dx > parentRight) {
                        removeAndRecycleView(it, recycler)
                        lastPos--
                    }
                }
            }
        }
        if (dx < 0) {
            val firstView = getChildAt(0)
            firstView?.let {
                val left = getDecoratedLeft(it)
                if (left > paddingLeft && firstPos > 0) {
                    firstPos--
                    val child = recycler.getViewForPosition(firstPos)
                    measureChildWithMargins(child, 0, 0)
                    val height = getDecoratedMeasuredHeight(child)
                    val width = getDecoratedMeasuredWidth(child)
                    addView(child, 0)
                    layoutDecorated(child, left - width, paddingTop, left, paddingTop + height)
                }
            }
        } else {
            val lastView = getChildAt(childCount - 1)
            lastView?.let {
                val right = getDecoratedRight(it)
                if (right < parentRight && lastPos < itemCount - 1) {
                    lastPos++
                    val child = recycler.getViewForPosition(lastPos)
                    measureChildWithMargins(child, 0, 0)
                    val height = getDecoratedMeasuredHeight(child)
                    val width = getDecoratedMeasuredWidth(child)
                    addView(child)
                    layoutDecorated(child, right, paddingTop, right + width, paddingTop + height)
                }
            }
        }
        scaleView()
        val firstView = getChildAt(0)
        firstView?.let {
            firstPos = getPosition(it)
            firstLeft = getDecoratedLeft(it) - paddingLeft
        }
        return dx
    }

    private fun scaleView() {
        val scaleF = 0.95f
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            view?.let {
                val width = getDecoratedMeasuredWidth(it)
                //        分两种情况，从右边到中间为变大，即是 scaleF到1.0f,从中间到左边即是1.0f到scaleF
                val centerViewX = it.left + width / 2
                //        子view的中心点到控件中心的距离
                val distance = abs(centerX - centerViewX).toFloat()

//        子view的中心点到控件中心的距离所占总view宽度的百分比
                val percent = distance / (getWidth() / 2).toDouble()
                val lastWidth = width * (1 - scaleF).toDouble()
                var realScaleF = ((width - percent * lastWidth) / width).toFloat()
                if (realScaleF < scaleF) {
                    realScaleF = scaleF
                } else if (realScaleF > 1.0f) {
                    realScaleF = 1.0f
                }
                //        X是1-100 而Y的值是80到100
                it.scaleX = realScaleF
                it.scaleY = realScaleF
            }
        }
    }

    init {
        orientation = HORIZONTAL
    }
}