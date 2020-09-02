package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.domain.domain.video.Video

class VideoApiGateway(private val videoApi: VideoApi) : VideoApiGatewayInterface {

    override suspend fun fetch(): List<Video> =
            videoApi.fetch().map { it.toVideo() }
}

private fun VideoJson.toVideo(): Video =
        Video(id, title, text)