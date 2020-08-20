package com.example.cleanarchitecture.feature.data.api

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

interface NewsApi {

    @GET("news")
    fun fetch(): Single<List<NewsJson>>
}