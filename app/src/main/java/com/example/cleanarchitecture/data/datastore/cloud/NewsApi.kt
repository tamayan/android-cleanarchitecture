package com.example.cleanarchitecture.data.datastore.cloud

import com.example.cleanarchitecture.data.entity.json.NewsResponseEntity
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

interface NewsApi {

    @GET("news")
    fun getNewsList(): Single<List<NewsResponseEntity>>
}