package com.tamayan.cleanarchitecture.di.module

import com.squareup.moshi.Moshi
import com.tamayan.cleanarchitecture.BuildConfig
import com.tamayan.cleanarchitecture.data.datastore.NewsDataStore
import com.tamayan.cleanarchitecture.data.datastore.ReadNewsDataStore
import com.tamayan.cleanarchitecture.data.datastore.cloud.CloudNewsDataStore
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.NewsApiClient
import com.tamayan.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.tamayan.cleanarchitecture.data.datastore.disk.DiskNewsDataStore
import com.tamayan.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.tamayan.cleanarchitecture.domain.repository.NewsRepository
import com.tamayan.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.tamayan.cleanarchitecture.presentation.mvp.view.NewsListView
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