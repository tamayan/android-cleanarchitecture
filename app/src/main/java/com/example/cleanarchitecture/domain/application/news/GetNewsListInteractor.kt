package com.example.cleanarchitecture.domain.application.news

import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.usecase.news.list.GetNewsListRequest
import com.example.cleanarchitecture.usecase.news.list.GetNewsListResponse
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
import com.example.cleanarchitecture.usecase.news.list.NewsListModel

/**
 * Created by tamayan on 2017/12/10.
 */

class GetNewsListInteractor(private val repository: NewsRepositoryInterface) : GetNewsListUseCase {

    override suspend fun handle(request: GetNewsListRequest): GetNewsListResponse =
            GetNewsListResponse(repository.findAll().map { NewsListModel(it.id, it.title) })
}