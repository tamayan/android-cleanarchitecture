package com.example.cleanarchitecture.di.module

import com.squareup.moshi.Moshi
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.ReadNewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.CloudNewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.client.NewsApiClient
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.DiskNewsDataStore
import com.example.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.example.cleanarchitecture.presentation.mvp.view.NewsListView
import dagger.Module
import dagger.Provides

/**
 * Created by tamayan on 2018/02/02.
 */

@Module
class NewsListActivityModule(private val newsListView: NewsListView) {

    @Provides
    fun provideNewsApiClient(moshi: Moshi, basicCredentialProvider: BasicCredentialProvider) =
            NewsApiClient(moshi, BuildConfig.BASE_URL, basicCredentialProvider)

    @Provides
    fun provideNewsDataStore(newsApiClient: NewsApiClient): NewsDataStore = CloudNewsDataStore(newsApiClient)

    @Provides
    fun provideReadNewsDataStore(): ReadNewsDataStore = DiskNewsDataStore()

    @Provides
    fun provideNewsRepository(newsDataStore: NewsDataStore, readNewsDataStore: ReadNewsDataStore): NewsRepository =
            NewsRepositoryImpl(newsDataStore, readNewsDataStore)

    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepository) = GetNewsListUseCase(newsRepository)

    @Provides
    fun provideNewsListView() = newsListView
}