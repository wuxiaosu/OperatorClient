package com.wuxiaosu.operatorclient.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.wuxiaosu.operatorclient.R
import java.util.*

/**
 *
 * Created by su on 2020/04/04.
 *  登录弹窗
 *
 */
class LoginDialogFragment : DialogFragment() {
    interface LoginListener {
        /**
         * 请求验证码
         *
         * @param phone
         */
        fun onReqCode(phone: String)

        /**
         * 登录
         *
         * @param phone
         * @param code
         */
        fun onRandomLogin(phone: String, code: String)
    }

    private var mLoginListener: LoginListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        @SuppressLint("InflateParams") val contentView = LayoutInflater.from(activity)
                .inflate(R.layout.fragment_login_dialog, null)
        val etPhone: TextInputEditText = contentView.findViewById(R.id.et_phone)
        val etCode: TextInputEditText = contentView.findViewById(R.id.et_code)
        val btnReqCode = contentView.findViewById<Button>(R.id.btn_req_code)
        val btnLogin = contentView.findViewById<Button>(R.id.btn_login)
        if (activity is LoginListener) {
            mLoginListener = activity as LoginListener?
        }
        btnReqCode.setOnClickListener {
            val phone = Objects.requireNonNull(etPhone.text).toString()
            if (phone.isNotBlank()) {
                if (mLoginListener != null) {
                    mLoginListener!!.onReqCode(phone)
                }
            }
        }
        btnLogin.setOnClickListener {
            val phone = Objects.requireNonNull(etPhone.text).toString()
            val code = Objects.requireNonNull(etCode.text).toString()
            if (phone.isNotBlank() && code.isNotBlank()) {
                if (mLoginListener != null) {
                    mLoginListener!!.onRandomLogin(phone, code)
                }
            }
        }
        return AlertDialog.Builder(activity!!)
                .setTitle(R.string.dialog_title_login)
                .setView(contentView)
                .setNegativeButton(R.string.dialog_action_cancel
                ) { dialog: DialogInterface?, which: Int -> }
                .create()
    }
}