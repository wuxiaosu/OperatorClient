package com.wuxiaosu.operatorclient.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.wuxiaosu.operatorclient.R
import kotlinx.android.synthetic.main.widget_phone_info.view.*


/**
 * Created by su on 2020/04/04.
 */
class PhoneInfoLayout(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    private var mActionClickListener: OnActionClickListener? = null

    interface OnActionClickListener {

        fun onClearClick()

        fun onRefreshClick()
    }

    init {
        val contentView = LayoutInflater.from(context)
                .inflate(R.layout.widget_phone_info, this)

        iv_clear.setOnClickListener {
            mActionClickListener?.onClearClick()
        }
        iv_refresh.setOnClickListener {
            mActionClickListener?.onRefreshClick()
        }
    }

    fun setOnActionClickListener(clickListener: OnActionClickListener) {
        this.mActionClickListener = clickListener
    }

    fun clearData() {
        tv_phone.text = "未登录"
        tv_flow.text = "--"
        tv_balance.text = "--"
        tv_min.text = "--"
        tv_update.text = "--"
    }

    fun setData(phone: String?, flow: String?, balance: String?, min: String?, update: String?) {
        tv_phone.text = phone
        tv_flow.text = flow
        tv_balance.text = balance
        tv_min.text = min
        tv_update.text = update
    }
}