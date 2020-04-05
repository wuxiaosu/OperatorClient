package com.wuxiaosu.operatorclient.http.resp

import com.google.gson.annotations.SerializedName


/**
 * Created by su on 2020/04/02.
 * {"rsp_code":"0000","rsp_desc":"验证码已发送"}
 */
data class SendRadomNumResp(
        @SerializedName("rsp_code")
        val rspCode: String,

        @SerializedName("rsp_desc")
        val rspDesc: String
)