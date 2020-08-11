package com.example.cleanarchitecture.di.module

import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.CloudNewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.client.NewsApiClient
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.NewsRoomDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDao
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDatabase
import com.example.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.example.cleanarchitecture.presentation.mvp.view.NewsListView
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by tamayan on 2018/02/02.
 */

@Module
class NewsListActivityModule(private val newsListView: NewsListView) {

    @Singleton
    @Provides
    fun provideNewsApiClient(moshi: Moshi, basicCredentialProvider: BasicCredentialProvider): NewsApiClient =
            NewsApiClient(moshi, BuildConfig.BASE_URL, basicCredentialProvider)

    @Singleton
    @Provides
    fun provideNewsDataStore(newsApiClient: NewsApiClient): NewsDataStore =
            CloudNewsDataStore(newsApiClient)

    @Singleton
    @Provides
    fun provideNewsDao(database: AppDatabase): NewsDao =
            database.newsDao()

    @Singleton
    @Provides
    fun provideNewsRoomDatabase(database: AppDatabase, newsDao: NewsDao): NewsDatabase =
            NewsRoomDatabase(database, newsDao)

    @Provides
    fun provideNewsRepository(newsDataStore: NewsDataStore, newsDatabase: NewsDatabase): NewsRepository =
            NewsRepositoryImpl(newsDataStore, newsDatabase)

    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepository) =
            GetNewsListUseCase(newsRepository)

    @Provides
    fun provideNewsListView() = newsListView
}