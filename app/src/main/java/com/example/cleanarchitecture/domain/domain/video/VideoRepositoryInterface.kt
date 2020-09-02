package com.example.cleanarchitecture.domain.domain.video

import kotlinx.coroutines.flow.Flow

interface VideoRepositoryInterface {

    suspend fun find(id: String): Video

    fun findAll(): Flow<List<Video>>
}