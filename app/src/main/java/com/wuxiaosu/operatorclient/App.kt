package com.wuxiaosu.operatorclient

import android.app.Application
import android.content.Context
import android.util.Base64
import com.google.gson.Gson
import com.wuxiaosu.operatorclient.bean.PhoneInfo
import com.wuxiaosu.operatorclient.util.SPUtils

/**
 * Created by su on 2020/1/2.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        sContext = this
    }

    companion object {

        var sContext: Context? = null

        fun getContext(): Context {
            return sContext!!
        }
    }

    object Session {
        private const val KEY_FIRST = "first"
        private const val KEY_FIRST_COOKIE = "first_cookie"
        private const val KEY_FIRST_PHONE_NUM = "first_phone_num"
        private const val KEY_FIRST_SHOW_NUM = "first_show_num"
        var firstPhone: PhoneInfo? = null
            get() {
                if (field == null) {
                    field = Gson().fromJson(String(Base64.decode(SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                            .getString(KEY_FIRST), Base64.DEFAULT)), PhoneInfo::class.java)
                }
                return field
            }
            set(firstPhone) {
                SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                        .put(KEY_FIRST, Base64.encodeToString(Gson().toJson(firstPhone).toByteArray(), Base64.DEFAULT))
                field = firstPhone
            }
        private var firstCookie: String? = null
        private var firstPhoneNum: String? = null
        private var firstShowNum: String? = null

        fun getFirstCookie(): String? {
            if (firstCookie == null) {
                firstCookie = String(Base64.decode(SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                        .getString(KEY_FIRST_COOKIE), Base64.DEFAULT))
            }
            return firstCookie
        }

        fun setFirstCookie(firstCookie: String?) {
            val v = if (firstCookie.isNullOrEmpty()) "" else
                Base64.encodeToString(firstCookie.toByteArray(), Base64.DEFAULT)
            SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                    .put(KEY_FIRST_COOKIE, v)
            Session.firstCookie = firstCookie
        }

        fun getFirstPhoneNum(): String? {
            if (firstPhoneNum == null) {
                firstPhoneNum = String(Base64.decode(SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                        .getString(KEY_FIRST_PHONE_NUM), Base64.DEFAULT))
            }
            return firstPhoneNum
        }

        fun setFirstPhoneNum(firstPhoneNum: String?) {
            val v = if (firstPhoneNum.isNullOrEmpty()) "" else
                Base64.encodeToString(firstPhoneNum.toByteArray(), Base64.DEFAULT)
            SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                    .put(KEY_FIRST_PHONE_NUM, v)
            Session.firstPhoneNum = firstPhoneNum
        }

        fun getFirstShowNum(): String? {
            if (firstShowNum == null) {
                firstShowNum = String(Base64.decode(SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                        .getString(KEY_FIRST_SHOW_NUM), Base64.DEFAULT))
            }
            return firstShowNum
        }

        fun setFirstShowNum(firstShowNum: String?) {
            val v = if (firstShowNum.isNullOrEmpty()) "" else
                Base64.encodeToString(firstShowNum.toByteArray(), Base64.DEFAULT)
            SPUtils.getInstance(BuildConfig.APPLICATION_ID)
                    .put(KEY_FIRST_SHOW_NUM, v)
            Session.firstShowNum = firstShowNum
        }
    }

}