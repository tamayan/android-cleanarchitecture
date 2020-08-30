package com.example.cleanarchitecture.usecase.news.detail

data class GetNewsDetailRequest(val id: Int)

data class GetNewsDetailResponse(
        val id: Int,
        val title: String,
        val text: String
)