package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.VideoRepository
import com.example.cleanarchitecture.data.api.VideoApiGatewayInterface
import com.example.cleanarchitecture.data.database.VideoDataStoreInterface
import com.example.cleanarchitecture.domain.domain.video.VideoRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideVideoRepository(apiGateway: VideoApiGatewayInterface, dataStore: VideoDataStoreInterface): VideoRepositoryInterface =
            VideoRepository(apiGateway, dataStore)
}