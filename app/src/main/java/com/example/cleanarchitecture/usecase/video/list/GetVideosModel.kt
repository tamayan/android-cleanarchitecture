package com.example.cleanarchitecture.usecase.video.list

import kotlinx.coroutines.flow.Flow

class GetVideosRequest

data class GetVideosResponse(val videos: Flow<List<VideoModel>>)

data class VideoModel(
        val id: String,
        val title: String
)