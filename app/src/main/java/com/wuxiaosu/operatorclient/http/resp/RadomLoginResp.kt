package com.wuxiaosu.operatorclient.http.resp

import com.google.gson.annotations.SerializedName

/**
 * Created by su on 2020/04/02.
 * {
 *  "menuNetType": "112",
 *  "invalidat": "2020-03-30 22:48:09",
 *  "token_online": "1d153e6ab4875a563cee2c98d7d1562907d5da701880b4d4f9340fbf5614f991f7d63da3fb7221a946703bc2c9811697d9c8378aaa684a62db937da5fe44b01b52fef47329653f55d62905d00cdd7e329d59d7551370661f5315e5995ba5bd33c18f5c325460300dac6bbf475dd4796767287b42f7bcf66dc85b7bd87f8520e3ec707a9ae956eaaa6323715998514df20826c01ab1845f5051095d74344ee8fc7aeee815b00d14f3906c2e6c6f4fef55a0dcce006aaac34fdaf6b1e8dcf3eb13835320a2a54a2137edd202afa580efb9ef3c18a6994a6398248e59d3c8c7a8ab5114dcd30ae2c8d4a0c63da01dbeb5b0ec311872204cbca59d6212da3cb34747338b8b7b9b4ea5dc53afb710708634aa",
 *  "t3_token": "d2a864693779779797d823523c72c0e9",
 *  "keyVersion": "1",
 *  "default": "18512345612",
 *  "filename": "",
 *  "pwd": "RpwNmH1uWa3KIeGDl9dM2LDhu/GM9GpT0m4Pz99R5dVGSqtS1CdcaSn2H1j1ag7q8tK26zgg/4AKwx6Yyp8/P1XpHzNXpb6ou2sKzwKCQ6uLVsQkCUuN6HTcCYY5bkay1P51qF3BmPyMNPIBasrljrScktni3FY8bSJvh+7jVrw=",
 *  "desmobile": "18512345612",
 *  "head_img": "",
 *  "appId": "bcbf9239dffadd5449045269ab5346e8840dcc41ac3f7db9ae0f30ff181b7edb4c71ff94d7cdc8e72a79152a7aeea803a29c754ac5d704e977cbb7d79a39d2cf",
 *  "ecs_token": "eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDlmOTE4ZTIxOGI3MDk2NGIxYzY5NThlZTUwN2U3ODhhYTI4NGVlOGYzYTg1NjkxNGY4ZGYxMTdhZmQxNjE2YWQwN2Q1N2QzY2RlNDA1MGIzNjQwN2YzMjRhMDMwNjViOTg0OTkzYTJiZWZlNzAyNjc5YzJhMzQyNGVhYzM0ZWMxIiwidmVyc2lvbiI6IjAwIn0=",
 *  "aha": "5daac077632bb2f57a013325b20832cff0c80879fdfa7cc4e2c4787a163fa117c81ffa00b286e41788dd9e3856587f38",
 *  "showFlower": "0",
 *  "code": "0",
 *  "menuurl": "http://m.client.10010.com:80/mobileService/templates/sitemap/",
 *  "yw_code": "",
 *  "list": [
 *      {
 *          "proCode": "051",
 *          "area_code": "",
 *          "num": "18512345612",
 *          "cityCode": "510",
 *          "proName": "广东",
 *          "cityName": "广州",
 *          "type": "01"
 *      }
 *  ],
 *  "type": "06"
 * }
 */
data class RadomLoginResp(
        @SerializedName("aha")
        val aha: String,

        @SerializedName("appId")
        val appId: String,

        @SerializedName("code")
        val code: String,

        @SerializedName("dsc")
        val dsc: String,

        @SerializedName("default")
        val default: String,

        @SerializedName("desmobile")
        val desMobile: String,

        @SerializedName("ecs_token")
        val ecsToken: String,

        @SerializedName("filename")
        val filename: String,

        @SerializedName("head_img")
        val headImg: String,

        @SerializedName("invalidat")
        val invaliDat: String,

        @SerializedName("keyVersion")
        val keyVersion: String,

        @SerializedName("list")
        val list: List<User>,

        @SerializedName("menuNetType")
        val menuNetType: String,

        @SerializedName("menuurl")
        val menuUrl: String,

        @SerializedName("pwd")
        val pwd: String,

        @SerializedName("showFlower")
        val showFlower: String,

        @SerializedName("t3_token")
        val t3Token: String,

        @SerializedName("token_online")
        val tokenOnline: String,

        @SerializedName("type")
        val type: String,

        @SerializedName("yw_code")
        val ywCode: String
)

data class User(

        @SerializedName("area_code")
        val areaCode: String,

        @SerializedName("cityCode")
        val cityCode: String,

        @SerializedName("cityName")
        val cityName: String,

        @SerializedName("num")
        val num: String,

        @SerializedName("proCode")
        val proCode: String,

        @SerializedName("proName")
        val proName: String,

        @SerializedName("type")
        val type: String
)