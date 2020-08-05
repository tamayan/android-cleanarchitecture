package com.example.cleanarchitecture.data.datastore.cloud.client.okhttp

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

/**
 * Created by tamayan on 2017/11/23.
 *
 * Basic認証を追加するInterceptorクラスです。
 */

class BasicAuthenticationInterceptor(private val basicCredentialProvider: BasicCredentialProvider) : Interceptor {

    override fun intercept(chain: Chain): Response {
        val host = chain.request().url().host()
        val credential = basicCredentialProvider.getCredentialFor(host)
        val builder = chain.request().newBuilder()

        builder.addHeader("Authorization", "Basic " + credential.encoded())
        return chain.proceed(builder.build())
    }
}