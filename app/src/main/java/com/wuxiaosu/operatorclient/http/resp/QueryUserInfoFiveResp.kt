package com.wuxiaosu.operatorclient.http.resp


/**
 * Created by su on 2020/04/04.
 */
data class QueryUserInfoFiveResp(
        val code: String,
        val configData: ConfigData,
        val data: Data,
        val downButtonImageUrl: String,
        val flush_date_time: String,
        val flush_date_time_color: String,
        val setButtonImageUrl: String,
        val upButtonImageUrl: String
)

data class ConfigData(
        val cityTextColor: String,
        val recommendBacColor: String,
        val recommendCloseImageUrl: String,
        val recommendIconUrl: String,
        val recommendTextColor: String,
        val scanCodeButtonImageUrl: String,
        val searchButtnImageUrl: String
)

data class Data(
        val ask_url: String,
        val dataList: List<DataX>,
        val levelLinkedAddress: String,
        val levelLinkedTitle: String,
        val levelPic: String,
        val mail_url: String,
        val showUserPicFlag: String,
        val subsidiaryNumberImages: String,
        val subsidiaryNumberUrl: String
)

data class DataX(
        val ballRippleColor1: String,
        val ballRippleColor2: String,
        val button: String,
        val buttonAddress: String,
        val buttonBacImageUrl: String,
        val buttonTextColor: String,
        val isWarn: String,
        val number: String,
        val persent: String,
        val persentColor: String,
        val pointUpdateTimeStamp: String,
        val remainTitle: String,
        val remainTitleColoer: String,
        val unit: String,
        val url: String,
        val usedTitle: String,
        val warningPointColor: String,
        val warningTextColor: String
)