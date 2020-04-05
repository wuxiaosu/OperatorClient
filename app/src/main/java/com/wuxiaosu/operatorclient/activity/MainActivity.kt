package com.wuxiaosu.operatorclient.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.koushikdutta.async.future.FutureCallback
import com.wuxiaosu.operatorclient.App
import com.wuxiaosu.operatorclient.R
import com.wuxiaosu.operatorclient.bean.PhoneInfo
import com.wuxiaosu.operatorclient.fragment.LoginDialogFragment
import com.wuxiaosu.operatorclient.fragment.LoginDialogFragment.LoginListener
import com.wuxiaosu.operatorclient.fragment.ProgressBarDialogFragment
import com.wuxiaosu.operatorclient.http.resp.DataX
import com.wuxiaosu.operatorclient.http.resp.QueryUserInfoFiveResp
import com.wuxiaosu.operatorclient.http.resp.RadomLoginResp
import com.wuxiaosu.operatorclient.http.resp.SendRadomNumResp
import com.wuxiaosu.operatorclient.util.HttpUtils
import com.wuxiaosu.operatorclient.widget.PhoneInfoLayout.OnActionClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LoginListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val mLoginDialogFragment: LoginDialogFragment by lazy { LoginDialogFragment() }
    private val mProgressBarDialogFragment: ProgressBarDialogFragment by lazy { ProgressBarDialogFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        fab.setOnClickListener {
            showLoginDialog()
        }
        pil_main.setOnActionClickListener(object : OnActionClickListener {
            override fun onClearClick() {
                App.Session.setFirstCookie(null)
                App.Session.setFirstPhoneNum(null)
                App.Session.firstPhone = null
            }

            override fun onRefreshClick() {
                tryRefresh(true)
            }
        })
        initData()
        tryRefresh()
    }

    private fun initData() {
        val first: PhoneInfo? = App.Session.firstPhone
        if (first != null) {
            pil_main.setData(if (!App.Session.getFirstShowNum().isNullOrEmpty())
                App.Session.getFirstShowNum() else first.phone, first.flow, first.balance, first.min, first.update)
        } else {
            pil_main.clearData()
        }
    }

    private fun tryRefresh(showNoLogin: Boolean = false) {
        val cookie: String? = App.Session.getFirstCookie();
        val phoneNum: String? = App.Session.getFirstPhoneNum();

        if (!cookie.isNullOrEmpty() && !phoneNum.isNullOrEmpty()) {
            fab.visibility = View.GONE
            queryUserInfoFive(phoneNum, cookie)
        } else {
            fab.visibility = View.VISIBLE

            if (showNoLogin) {
                Toast.makeText(this@MainActivity,
                        R.string.toast_un_login, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onReqCode(phone: String) {
        showProgressBarDialog()
        HttpUtils.sendRadomNum(phone, FutureCallback { _, result ->
            Log.d(TAG, "sendRadomNum: $result")
            dismissProgressBarDialog()
            val (rspCode, rspDesc) =
                    Gson().fromJson(result, SendRadomNumResp::class.java)
            if ("0000" == rspCode) {
                if (!this@MainActivity.isDestroyed) {
                    if (rspDesc.isNotEmpty()) {
                        Toast.makeText(this@MainActivity,
                                rspDesc, Toast.LENGTH_SHORT).show()
                    }
                }
                return@FutureCallback
            }
            if (rspDesc.isNotBlank()) {
                showReqFailToast(rspDesc)
            } else {
                showReqFailToast()
            }
        })
    }

    override fun onRandomLogin(phone: String, code: String) {
        showProgressBarDialog()
        HttpUtils.radomLogin(phone, code, FutureCallback { _, result ->
            dismissProgressBarDialog()
            if (result == null) {
                showReqFailToast()
                return@FutureCallback
            }
            Log.d(TAG, "header: " + result.headers.headers.toString())
            Log.d(TAG, "radomLogin: " + result.result)

            val resp =
                    Gson().fromJson(result.result, RadomLoginResp::class.java)
            if ("0" == resp?.code) {
                val sb = StringBuilder()
                for (s in result.headers.headers.getAll("Set-Cookie")) {
                    sb.append(s).append("; ")
                }
                App.Session.setFirstPhoneNum(phone)
                resp.list[0].let {
                    App.Session.setFirstShowNum(resp.list[0].proName
                            + resp.list[0].cityName + "\n" + phone)
                }
                App.Session.setFirstCookie(sb.toString())
                dismissLoginDialog()
                tryRefresh()
                return@FutureCallback
            }
            showReqFailToast()
        })
    }

    fun queryUserInfoFive(phone: String, cookie: String) {
        showProgressBarDialog()
        HttpUtils.queryUserInfoFive(phone, cookie, FutureCallback { _, result ->
            dismissProgressBarDialog()
            Log.d(TAG, "queryUserInfoFive: " + result)
            val resp =
                    Gson().fromJson(result, QueryUserInfoFiveResp::class.java)
            if ("Y".equals(resp.code, true) && !resp.flush_date_time.isEmpty()) {
                var flow: String? = null
                var balance: String? = null
                var min: String? = null
                val update: String? = resp.flush_date_time
                val dataList: List<DataX>? = resp?.data?.dataList
                if (dataList != null) {
                    for (item: DataX in dataList) {
                        val number = item.number // 4.70
                        val remainTitle = item.remainTitle // 剩余流量
                        val unit = item.unit // GB

                        if (remainTitle.contains("流量")) {
                            flow = number + unit
                        } else if (remainTitle.contains("话费")) {
                            balance = number + unit
                        } else if (remainTitle.contains("语音")) {
                            min = number + unit
                        }
                    }
                }
                App.Session.firstPhone = PhoneInfo(phone, flow, balance, min, update)
                initData()
            } else {
                showReqFailToast()
            }
        })
    }

    private fun showProgressBarDialog() {
        mProgressBarDialogFragment.show(supportFragmentManager, "ProgressBar")
    }

    private fun dismissProgressBarDialog() {
        mProgressBarDialogFragment.dismiss()
    }

    private fun showLoginDialog() {
        mLoginDialogFragment.show(supportFragmentManager, "Login")
    }

    private fun dismissLoginDialog() {
        mLoginDialogFragment.dismiss()
    }

    private fun showReqFailToast(msg: String = getString(R.string.toast_req_fail)) {
        if (!isDestroyed) {
            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}



