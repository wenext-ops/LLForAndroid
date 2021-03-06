package com.tenext.demo.adapter

import android.content.Context
import android.view.ViewGroup
import com.tenext.demo.R
import com.tenext.demo.holder.BaseHolder
import com.tenext.demo.holder.EnumHolder
import com.tenext.demo.popup.EnumPopupWindow

class EnumAdapter : BaseAdapter {

    private var window: EnumPopupWindow

    constructor(context: Context, window: EnumPopupWindow, list: List<Any>) : super(context, list) {
        this.window = window
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): BaseHolder<*> {
        return EnumHolder(mContext,window, parent, R.layout.item_enum)
    }
}