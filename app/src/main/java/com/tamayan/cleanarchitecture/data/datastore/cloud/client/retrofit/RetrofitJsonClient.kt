package com.tamayan.cleanarchitecture.data.datastore.cloud.client.retrofit

import com.squareup.moshi.Moshi
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

abstract class RetrofitJsonClient<in RequestEntity, ResponseEntity> (private val moshi: Moshi,
                                                                    baseUrl: String,
                                                                    basicCredentialProvider: BasicCredentialProvider) :
        RetrofitClient<RequestEntity, ResponseEntity>(baseUrl, basicCredentialProvider) {

    override fun buildRetrofit(builder: Builder): Retrofit {
        return builder
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
    }
}