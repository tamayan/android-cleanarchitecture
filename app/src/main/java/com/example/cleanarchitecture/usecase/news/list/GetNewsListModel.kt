package com.example.cleanarchitecture.usecase.news.list

class GetNewsListRequest

data class GetNewsListResponse(val newsListModel: List<NewsListModel>)

data class NewsListModel(
        val id: Int,
        val title: String
)