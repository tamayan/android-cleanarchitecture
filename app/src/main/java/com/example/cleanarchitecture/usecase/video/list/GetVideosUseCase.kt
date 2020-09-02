package com.example.cleanarchitecture.usecase.video.list

interface GetVideosUseCase {

    fun handle(request: GetVideosRequest): GetVideosResponse
}