package com.example.cleanarchitecture.data

import com.example.cleanarchitecture.data.api.VideoApiGatewayInterface
import com.example.cleanarchitecture.data.database.VideoDataStoreInterface
import com.example.cleanarchitecture.domain.domain.video.Video
import com.example.cleanarchitecture.domain.domain.video.VideoRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideoRepository(private val apiGateway: VideoApiGatewayInterface,
                      private val dataStore: VideoDataStoreInterface) : VideoRepositoryInterface {

    override suspend fun find(id: String): Video =
            dataStore.find(id)

    override fun findAll(): Flow<List<Video>> =
            flow {
                // APIから取得
                val videos = apiGateway.fetch()
                // DBに保存
                dataStore.save(videos)
                emit(videos)
            }.catch {
                // APIから取得に失敗した場合、DBから取得
                emit(dataStore.findAll())
            }.flowOn(Dispatchers.IO) // 上部のスレッドを切り替え
}