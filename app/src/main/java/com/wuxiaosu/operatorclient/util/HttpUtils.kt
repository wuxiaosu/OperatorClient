package com.wuxiaosu.operatorclient.util

import com.koushikdutta.async.future.FutureCallback
import com.koushikdutta.ion.Ion
import com.koushikdutta.ion.Response
import com.wuxiaosu.operatorclient.App
import com.wuxiaosu.operatorclient.util.RSAUtils.encryptBase64
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by su on 2020/1/2.
 */
object HttpUtils {

    private const val VERSION = "android@5.94"

    fun sendRadomNum(phone: String, callback: FutureCallback<String?>) {
        Ion.with(App.getContext())
                .load("http://m.client.10010.com/mobileService/sendRadomNum.htm")
                .setBodyParameter("mobile", encryptBase64(phone))
                .setBodyParameter("keyVersion", "")
                .setBodyParameter("version", VERSION)
                .asString()
                .setCallback(callback)
    }

    fun radomLogin(phone: String, password: String, callback: FutureCallback<Response<String?>?>) {
        val random = Random()
        val r = (random.nextInt(9).toString() + ""
                + random.nextInt(9) + ""
                + random.nextInt(9) + ""
                + random.nextInt(9) + ""
                + random.nextInt(9) + ""
                + random.nextInt(9))
        Ion.with(App.getContext())
                .load("http://m.client.10010.com/mobileService/radomLogin.htm").setHeader()
                .setBodyParameter("isRemberPwd", "true")
                .setBodyParameter("loginStyle", "0")
                .setBodyParameter("deviceId", DeviceUtils.deviceId)
                .setBodyParameter("netWay", DeviceUtils.networkInfo)
                .setBodyParameter("mobile", encryptBase64(phone + r))
                .setBodyParameter("password", encryptBase64(password + r))
                .setBodyParameter("yw_code", "")
                .setBodyParameter("timestamp", SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(Date()))
                .setBodyParameter("appId", "bcbf9239dffadd5449045269ab5346e8840dcc41ac3f7db9ae0f30ff181b7edb4c71ff94d7cdc8e72a79152a7aeea803a29c754ac5d704e977cbb7d79a39d2cf")
                .setBodyParameter("keyVersion", "")
                .setBodyParameter("deviceBrand", DeviceUtils.deviceBrand)
                .setBodyParameter("version", VERSION)
                .setBodyParameter("pip", DeviceUtils.ip)
                .setBodyParameter("provinceChanel", "general")
                .setBodyParameter("deviceModel", DeviceUtils.deviceModel)
                .setBodyParameter("deviceOS", DeviceUtils.deviceOS)
                .setBodyParameter("deviceCode", DeviceUtils.deviceId)
                .asString()
                .withResponse()
                .setCallback(callback)
    }

    fun queryUserInfoFive(phone: String?, cookie: String, callback: FutureCallback<String?>?) {
        Ion.with(App.getContext())
                .load("https://m.client.10010.com/mobileService/home/queryUserInfoFive.htm")
                .setHeader("Cookie", cookie)
                .setBodyParameter("currentPhone", phone)
                .setBodyParameter("showType", "3")
                .setBodyParameter("version", VERSION)
                .setBodyParameter("desmobile", phone)
                .asString()
                .setCallback(callback)
    }
}