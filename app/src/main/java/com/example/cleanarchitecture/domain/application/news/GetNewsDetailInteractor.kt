package com.example.cleanarchitecture.domain.application.news

import com.example.cleanarchitecture.domain.domain.news.News
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailRequest
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailResponse
import com.example.cleanarchitecture.usecase.news.detail.GetNewsDetailUseCase

class GetNewsDetailInteractor(private val newsRepository: NewsRepositoryInterface) : GetNewsDetailUseCase {

    override suspend fun handle(requestModel: GetNewsDetailRequest): GetNewsDetailResponse =
            newsRepository
                    .find(requestModel.id)
                    .toResponse()
}

private fun News.toResponse(): GetNewsDetailResponse =
        GetNewsDetailResponse(id, title, text)