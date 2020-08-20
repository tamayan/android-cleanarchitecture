package com.example.cleanarchitecture.feature.usecase.news.list

data class GetNewsListResponse(val newsListModel: List<NewsListModel>)

data class NewsListModel(
        val id: Int,
        val title: String
)