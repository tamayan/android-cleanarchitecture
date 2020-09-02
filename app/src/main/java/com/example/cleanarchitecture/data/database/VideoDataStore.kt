package com.example.cleanarchitecture.data.database

import androidx.room.withTransaction
import com.example.cleanarchitecture.domain.domain.video.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VideoDataStore(private val appDatabase: AppDatabase,
                     private val videoDao: VideoDao = appDatabase.videoDao()) : VideoDataStoreInterface {

    override suspend fun find(id: String): Video =
            videoDao.find(id).toVideo()

    override fun findAll(): Flow<List<Video>> =
            videoDao.findAll().map { it.map { entity -> entity.toVideo() } }

    override suspend fun save(videos: List<Video>) =
            appDatabase.withTransaction {
                videoDao.insertOrUpdate(videos.map { it.toEntity() })
            }
}

private fun VideoEntity.toVideo(): Video =
        Video(id, title, text)

private fun Video.toEntity(): VideoEntity =
        VideoEntity(id, title, text)
