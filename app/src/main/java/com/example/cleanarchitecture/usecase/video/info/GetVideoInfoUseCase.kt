package com.example.cleanarchitecture.usecase.video.info

interface GetVideoInfoUseCase {

    suspend fun handle(request: GetVideoInfoRequest): GetVideoInfoResponse
}