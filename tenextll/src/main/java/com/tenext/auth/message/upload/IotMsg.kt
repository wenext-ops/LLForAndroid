package com.tenext.auth.message.upload

import com.tenext.auth.IoTAuth

open class IotMsg {

    var reqId = -1

    internal var action = ""

    internal var commonParams = ParamMap()

    private val params = ParamMap()

    init {
        params["params"] = commonParams
        commonParams["AppKey"] = IoTAuth.APP_KEY
    }

    override fun toString(): String {
        params["action"] = action
        params["reqId"] = reqId
        commonParams.toString()
        return params.toString()
    }

    open fun getMyAction():String{
        return action
    }

}