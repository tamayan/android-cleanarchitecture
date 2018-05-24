package com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp

import android.util.Base64

/**
 * Created by tamayan on 2017/11/23.
 *
 * Basic認証
 */

interface BasicCredentialProvider {

    fun getCredentialFor(host: String): BasicCredential

    class BasicCredential(
            private val username: String,
            private val password: String) {

        fun encoded(): String {
            val rawCredential = "$username:$password"
            return Base64.encodeToString(rawCredential.toByteArray(), Base64.NO_WRAP)
        }
    }
}