package com.wuxiaosu.operatorclient.util

import android.util.Base64
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

/**
 * Created by su on 2020-01-02.
 */
object RSAUtils {

    private val KEY = Base64.decode("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDc+CZK9bBA9IU+gZUOc6FUGu7yO9WpTNB0PzmgFBh96Mg1WrovD1oqZ+eIF4LjvxKXGOdI79JRdve9NPhQo07+uqGQgE4imwNnRx7PFtCRryiIEcUoavuNtuRVoBAm6qdB0SrctgaqGfLgKvZHOnwTjyNqjBUxzMeQlEC2czEMSwIDAQAB", Base64.NO_WRAP)

    @Throws(Exception::class)
    private fun encrypt(bytes: ByteArray?, key: ByteArray?): ByteArray {
        val generatePublic = KeyFactory.getInstance("RSA").generatePublic(X509EncodedKeySpec(key))
        val instance = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        instance.init(1, generatePublic)
        return instance.doFinal(bytes)
    }

    @JvmStatic
    fun encryptBase64(data: String): String {
        try {
            return Base64.encodeToString(encrypt(data.toByteArray(StandardCharsets.UTF_8), KEY), Base64.NO_WRAP)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }
}