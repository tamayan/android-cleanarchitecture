package com.example.cleanarchitecture.feature.data.api

import com.example.cleanarchitecture.feature.domain.domain.news.News
import io.reactivex.Single

/**
 * Created by tamayan on 2017/11/23.
 */

interface NewsApiGatewayInterface {

    fun getNewsList(): Single<List<News>>
}