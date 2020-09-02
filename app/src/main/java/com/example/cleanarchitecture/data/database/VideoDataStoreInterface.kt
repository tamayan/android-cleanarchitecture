package com.example.cleanarchitecture.data.database

import com.example.cleanarchitecture.domain.domain.video.Video
import kotlinx.coroutines.flow.Flow

interface VideoDataStoreInterface {

    suspend fun find(id: String): Video

    fun findAll(): Flow<List<Video>>

    suspend fun save(videos: List<Video>)
}