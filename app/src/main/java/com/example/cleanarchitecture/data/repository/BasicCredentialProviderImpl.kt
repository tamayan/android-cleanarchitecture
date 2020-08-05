package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider.BasicCredential

/**
 * Created by tamayan on 2017/11/23.
 */

class BasicCredentialProviderImpl : BasicCredentialProvider {

    override fun getCredentialFor(host: String): BasicCredential {
        return BasicCredential(BuildConfig.BASIC_USER_NAME, BuildConfig.BASIC_PASS)
    }
}