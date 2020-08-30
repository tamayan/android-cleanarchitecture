package com.example.cleanarchitecture.usecase.news.detail

interface GetNewsDetailUseCase {

    suspend fun handle(requestModel: GetNewsDetailRequest): GetNewsDetailResponse
}