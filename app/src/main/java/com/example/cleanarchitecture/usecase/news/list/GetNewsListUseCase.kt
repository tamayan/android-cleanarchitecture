package com.example.cleanarchitecture.usecase.news.list

interface GetNewsListUseCase {

    fun handle(request: GetNewsListRequest): GetNewsListResponse
}