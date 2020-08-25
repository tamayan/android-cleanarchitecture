package com.example.cleanarchitecture.usecase.news.list

import kotlinx.coroutines.flow.Flow

class GetNewsListRequest

data class GetNewsListResponse(val newsListModels: Flow<List<NewsListModel>>)

data class NewsListModel(
        val id: Int,
        val title: String
)