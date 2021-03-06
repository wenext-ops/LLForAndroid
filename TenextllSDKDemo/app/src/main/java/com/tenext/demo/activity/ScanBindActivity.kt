package com.tenext.demo.activity

import android.text.TextUtils
import com.tenext.auth.IoTAuth
import com.tenext.auth.callback.MyCallback
import com.tenext.auth.response.BaseResponse
import com.tenext.demo.App
import com.tenext.demo.R
import com.tenext.demo.log.L
import kotlinx.android.synthetic.main.activity_scan_bind.*
import kotlinx.android.synthetic.main.menu_back_layout.*

/**
 * 扫码绑定设备
 */
class ScanBindActivity : BaseActivity() {

    override fun getContentView(): Int {
        return R.layout.activity_scan_bind
    }

    override fun initView() {
        tv_title.text = "扫码绑定"
    }

    override fun setListener() {
        iv_back.setOnClickListener { finish() }
        btn_scan_bind.setOnClickListener {
            bindDevice()
        }
    }

    private fun bindDevice() {
        val signature = et_scan_signature.text.trim().toString()
        if (TextUtils.isEmpty(signature)) {
            show("请输入设备签名")
            return
        }
        App.data.getCurrentFamily().run {
            IoTAuth.deviceImpl.scanBindDevice(FamilyId, signature, object : MyCallback {
                override fun fail(msg: String?, reqCode: Int) {
                    L.e(msg ?: "")
                    show(msg ?: "绑定失败")
                }

                override fun success(response: BaseResponse, reqCode: Int) {
                    if (response.isSuccess()) {
                        show("绑定成功")
                    }else{
                        show(response.msg)
                    }
                }
            })
        }
    }
}
