package com.example.cleanarchitecture.domain.application.news

import com.example.cleanarchitecture.domain.domain.news.News
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListResponse
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel
import kotlinx.coroutines.flow.map

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListInteractor(private val repository: NewsRepositoryInterface) : GetNewsListUseCase {

    override suspend fun handle(request: GetNewsListRequest): GetNewsListResponse =
            GetNewsListResponse(repository.findAll().map { it.map { it.toModel() } })
}

private fun News.toModel(): NewsListModel =
        NewsListModel(id, title)