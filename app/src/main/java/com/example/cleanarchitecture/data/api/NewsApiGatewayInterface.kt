package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.domain.domain.news.News

/**
 * Created by tamayan on 2017/11/23.
 */

interface NewsApiGatewayInterface {

    suspend fun getNewsList(): List<News>
}