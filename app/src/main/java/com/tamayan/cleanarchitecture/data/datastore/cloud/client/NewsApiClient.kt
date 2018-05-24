package com.tamayan.cleanarchitecture.data.datastore.cloud.client

import com.squareup.moshi.Moshi
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.retrofit.RetrofitJsonClient
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.retrofit.service.NewsService
import com.tamayan.cleanarchitecture.data.entity.json.NewsRequestEntity
import com.tamayan.cleanarchitecture.data.entity.json.NewsResponseListEntity
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class NewsApiClient(moshi: Moshi, baseUrl: String, basicCredentialProvider: BasicCredentialProvider) :
        RetrofitJsonClient<NewsRequestEntity, NewsResponseListEntity>(moshi, baseUrl, basicCredentialProvider) {

    override fun requestActual(retrofit: Retrofit, requestEntity: NewsRequestEntity): Response<NewsResponseListEntity> {
        return retrofit
                .create(NewsService::class.java)
                .getNewsList()
                .execute()
    }
}