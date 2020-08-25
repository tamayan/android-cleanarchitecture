package com.example.cleanarchitecture.data.api

import retrofit2.http.GET

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

interface NewsApi {

    @GET("news")
    suspend fun fetch(): List<NewsJson>
}