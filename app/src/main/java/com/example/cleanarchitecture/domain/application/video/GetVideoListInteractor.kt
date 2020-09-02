package com.example.cleanarchitecture.domain.application.video

import com.example.cleanarchitecture.domain.domain.video.Video
import com.example.cleanarchitecture.domain.domain.video.VideoRepositoryInterface
import com.example.cleanarchitecture.usecase.video.list.GetVideosRequest
import com.example.cleanarchitecture.usecase.video.list.GetVideosResponse
import com.example.cleanarchitecture.usecase.video.list.GetVideosUseCase
import com.example.cleanarchitecture.usecase.video.list.VideoModel
import kotlinx.coroutines.flow.map

class GetVideoListInteractor(private val repository: VideoRepositoryInterface) : GetVideosUseCase {

    override fun handle(request: GetVideosRequest): GetVideosResponse =
            GetVideosResponse(repository.findAll().map { it.map { it.toModel() } })
}

private fun Video.toModel(): VideoModel =
        VideoModel(id, title)