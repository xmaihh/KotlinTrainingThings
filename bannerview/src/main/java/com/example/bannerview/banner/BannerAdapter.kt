package com.example.bannerview.banner

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

class BannerAdapter internal constructor(private val itemNum: Int, views: List<View>) : PagerAdapter() {
    private var viewList: List<View> = mutableListOf()
    private val tabNum: Int

    init {
        viewList = views
        this.tabNum = views.size
    }

    override fun getCount(): Int {
        return itemNum
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = viewList[position % tabNum]
        if (container == view.parent) {
            container.removeView(view)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {}
}
