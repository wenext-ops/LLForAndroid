package com.tenext.demo.activity

import com.tenext.auth.IoTAuth
import com.tenext.auth.callback.MyCallback
import com.tenext.auth.consts.RequestCode
import com.tenext.auth.entity.Device
import com.tenext.auth.entity.Family
import com.tenext.auth.response.BaseResponse
import com.tenext.demo.R
import com.tenext.demo.log.L
import com.tenext.demo.response.UserInfoResponse
import kotlinx.android.synthetic.main.activity_invite_member.*
import kotlinx.android.synthetic.main.menu_back_layout.*

/**
 * 邀请家庭成员
 */
class InviteMemberActivity : BaseActivity(), MyCallback {

    private var account = ""

    override fun getContentView(): Int {
        return R.layout.activity_invite_member
    }

    override fun initView() {
        tv_title.text = getString(R.string.invite_member)
    }

    override fun setListener() {
        iv_back.setOnClickListener { finish() }
        tv_invite_commit.setOnClickListener {
            account = et_invite_account.text.toString().trim()
            findUserID()
        }
    }

    /**
     *  查找用户ID
     */
    private fun findUserID() {
//        if (account.contains("@")) {//邮箱
        if (!account.matches(Regex("\\d+"))) {//邮箱
            if (account.matches(Regex("^\\w+@(\\w+\\.)+\\w+$"))) {
                IoTAuth.userImpl.findEmailUser(account, this)
            } else {
                show("邮箱地址不正确")
            }
        } else {
            if (account.length == 11) {
                IoTAuth.userImpl.findPhoneUser(account, "86", this)
            } else {
                show("手机号不正确")
            }
        }
    }

    /**
     * 发送邀请进入家庭的邀请
     */
    private fun sendFamilyInvite(userId: String) {
        get<Family>("family")?.run {
            IoTAuth.memberImpl.sendFamilyInvite(FamilyId, userId, this@InviteMemberActivity)
        }
    }

    override fun fail(msg: String?, reqCode: Int) {
        L.e(msg ?: "")
    }

    override fun success(response: BaseResponse, reqCode: Int) {
        if (response.isSuccess()) {
            when (reqCode) {
                RequestCode.find_phone_user, RequestCode.find_email_user -> {
                    response.parse(UserInfoResponse::class.java)?.Data?.run {
                        sendFamilyInvite(UserID)
                    }
                }
                RequestCode.send_family_invite -> {
                    show("邀请发送成功")
                }
            }
        } else {
            show(response.msg)
        }
    }
}
