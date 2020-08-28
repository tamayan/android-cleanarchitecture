package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.domain.application.news.GetNewsListInteractor
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
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
    fun provideGetNewsListUseCase(newsRepository: NewsRepositoryInterface): GetNewsListUseCase =
            GetNewsListInteractor(newsRepository)
}