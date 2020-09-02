package com.example.cleanarchitecture.domain.application.video

import com.example.cleanarchitecture.domain.domain.video.Video
import com.example.cleanarchitecture.domain.domain.video.VideoRepositoryInterface
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoRequest
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoResponse
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoUseCase

class GetVideoInfoInteractor(private val repository: VideoRepositoryInterface) : GetVideoInfoUseCase {

    override suspend fun handle(request: GetVideoInfoRequest): GetVideoInfoResponse =
            repository
                    .find(request.id)
                    .toResponse()
}

private fun Video.toResponse(): GetVideoInfoResponse =
        GetVideoInfoResponse(id, title, text)