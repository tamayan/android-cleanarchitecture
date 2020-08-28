package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.data.NewsRepository
import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
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
    fun provideNewsRepositoryImpl(apiGateway: NewsApiGatewayInterface, dataStore: NewsDataStoreInterface): NewsRepositoryInterface =
            NewsRepository(apiGateway, dataStore)
}