package com.wuxiaosu.operatorclient.util

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.telephony.TelephonyManager
import com.wuxiaosu.operatorclient.App

/**
 * Created by su on 2020/4/1.
 */
object DeviceUtils {
    /**
     * 获取 deviceId ，没权限就返回 “”
     *
     * @return deviceId or “”
     */
    @JvmStatic
    val deviceId: String
        @SuppressLint("HardwareIds", "MissingPermission")
        get() {
            return try {
                val telephonyManager = App.getContext().getSystemService(Context.TELEPHONY_SERVICE)
                        as TelephonyManager
                val deviceId = telephonyManager.deviceId
                if (!deviceId.isNullOrEmpty()) {
                    return deviceId
                }
                val subscriberId = telephonyManager.subscriberId
                if (!subscriberId.isNullOrEmpty()) {
                    subscriberId
                } else subscriberId
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }

    @JvmStatic
    val networkInfo: String
        get() {
            return try {
                val activeNetworkInfo = (App.getContext().getSystemService(Context.CONNECTIVITY_SERVICE)
                        as ConnectivityManager).activeNetworkInfo
                activeNetworkInfo?.let {
                    if (activeNetworkInfo.isConnected) {
                        when (activeNetworkInfo.type) {
                            ConnectivityManager.TYPE_MOBILE ->
                                return activeNetworkInfo.extraInfo
                            ConnectivityManager.TYPE_WIFI ->
                                return activeNetworkInfo.typeName
                        }
                    }
                }
                ""
            } catch (e: Exception) {
                e.printStackTrace()
                ""
            }
        }


    @JvmStatic
    val deviceBrand: String
        get() = try {
            Build.MANUFACTURER ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    @JvmStatic
    val deviceModel: String
        get() = try {
            Build.MODEL ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }

    @JvmStatic
    val deviceOS: String
        get() = try {
            "android" + Build.VERSION.RELEASE
        } catch (e: Exception) {
            ""
        }

    @JvmStatic
    val ip: String
        get() {
            return ""
        }
}