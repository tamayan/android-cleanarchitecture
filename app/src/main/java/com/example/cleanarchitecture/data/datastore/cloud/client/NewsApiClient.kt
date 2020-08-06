package com.example.cleanarchitecture.data.datastore.cloud.client

import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.cloud.client.retrofit.RetrofitJsonClient
import com.example.cleanarchitecture.data.datastore.cloud.client.retrofit.service.NewsService
import com.example.cleanarchitecture.data.entity.json.NewsRequestEntity
import com.example.cleanarchitecture.data.entity.json.NewsResponseEntity
import com.squareup.moshi.Moshi
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class NewsApiClient(moshi: Moshi, baseUrl: String, basicCredentialProvider: BasicCredentialProvider) :
        RetrofitJsonClient<NewsRequestEntity, List<NewsResponseEntity>>(moshi, baseUrl, basicCredentialProvider) {

    override fun requestActual(retrofit: Retrofit, requestEntity: NewsRequestEntity): Response<List<NewsResponseEntity>> {
        return retrofit
                .create(NewsService::class.java)
                .getNewsList()
                .execute()
    }
}