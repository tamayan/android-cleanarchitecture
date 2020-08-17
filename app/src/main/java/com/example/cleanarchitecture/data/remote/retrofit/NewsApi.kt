package com.example.cleanarchitecture.data.remote.retrofit

import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

interface NewsApi {

    @GET("news")
    fun getNewsList(): Single<List<NewsJson>>
}