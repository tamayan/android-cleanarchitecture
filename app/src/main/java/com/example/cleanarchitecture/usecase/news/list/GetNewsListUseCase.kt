package com.example.cleanarchitecture.usecase.news.list

interface GetNewsListUseCase {

    suspend fun handle(request: GetNewsListRequest): GetNewsListResponse
}