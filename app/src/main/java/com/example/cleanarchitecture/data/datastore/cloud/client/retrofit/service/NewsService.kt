package com.example.cleanarchitecture.data.datastore.cloud.client.retrofit.service

import com.example.cleanarchitecture.data.entity.json.NewsResponseEntity
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

interface NewsService {

    @GET("news")
    fun getNewsList(): Call<List<NewsResponseEntity>>
}