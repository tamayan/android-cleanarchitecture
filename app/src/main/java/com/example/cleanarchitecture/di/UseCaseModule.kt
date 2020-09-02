package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.domain.application.video.GetVideoInfoInteractor
import com.example.cleanarchitecture.domain.application.video.GetVideoListInteractor
import com.example.cleanarchitecture.domain.domain.video.VideoRepositoryInterface
import com.example.cleanarchitecture.usecase.video.info.GetVideoInfoUseCase
import com.example.cleanarchitecture.usecase.video.list.GetVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetVideoInfoInteractor(repository: VideoRepositoryInterface): GetVideoInfoUseCase =
            GetVideoInfoInteractor(repository)

    @Singleton
    @Provides
    fun provideGetVideoListInteractor(repository: VideoRepositoryInterface): GetVideosUseCase =
            GetVideoListInteractor(repository)
}