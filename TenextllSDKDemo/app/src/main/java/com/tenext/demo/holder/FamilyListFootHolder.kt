package com.tenext.demo.holder

import android.content.Context
import android.view.ViewGroup
import com.tenext.auth.entity.Family
import kotlinx.android.synthetic.main.foot_family_list.view.*

/**
 * 家庭 管理底部
 */
class FamilyListFootHolder : BaseHolder<Family> {

    constructor(context: Context, parent: ViewGroup, resId: Int) : super(context, parent, resId)

    override fun show(holder: BaseHolder<*>, position: Int) {
        itemView.tv_add_family.setOnClickListener {
            clickItem(holder, it, position)
        }
    }

}