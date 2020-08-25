package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.domain.domain.news.News

/**
 * Created by MSnowRobin016 on 2017/11/25.
 */

class NewsApiGateway(private val newsApi: NewsApi) : NewsApiGatewayInterface {

    override suspend fun getNewsList(): List<News> =
            newsApi.fetch().map { it.toNews() }
}

private fun NewsJson.toNews(): News =
        News(id, title, text)