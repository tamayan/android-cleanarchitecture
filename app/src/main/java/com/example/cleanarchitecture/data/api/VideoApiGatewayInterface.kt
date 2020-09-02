package com.example.cleanarchitecture.data.api

import com.example.cleanarchitecture.domain.domain.video.Video

interface VideoApiGatewayInterface {

    suspend fun fetch(): List<Video>
}