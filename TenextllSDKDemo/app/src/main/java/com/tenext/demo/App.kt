package com.tenext.demo

import android.app.Application
import android.util.Log
import com.tenext.auth.IoTAuth
import com.tenext.auth.entity.User
import com.tenext.auth.listener.LoginExpiredListener
import com.tenext.demo.log.L

class App : Application() {

    companion object {
        val data = AppData.instance
    }

    private val APP_KEY = "aBCuYQcbDMGlzZTMU"

    override fun onCreate() {
        super.onCreate()
        L.isLog = true
        //需要打印日志时要在IoTAuth.init(APP_KEY)之前调用
        // 否则看不到"The SDK initialized successfully"的日志
        IoTAuth.openLog(true)
        IoTAuth.init(APP_KEY)
        IoTAuth.addLoginExpiredListener(object : LoginExpiredListener {
            override fun expired(user: User) {
                L.d("用户登录过期")
            }
        })
    }

    override fun onTerminate() {
        IoTAuth.destroy()
        super.onTerminate()
    }
}