package com.example.cleanarchitecture.usecase.video.info

data class GetVideoInfoRequest(val id: String)

data class GetVideoInfoResponse(
        val id: String,
        val title: String,
        val text: String
)