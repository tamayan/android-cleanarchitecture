package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.video.Video

interface VideoDataStoreInterface {

    suspend fun find(id: String): Video

    suspend fun findAll(): List<Video>

    suspend fun save(videos: List<Video>)
}