package com.example.cleanarchitecture.feature.di.news

import com.example.cleanarchitecture.feature.domain.application.GetNewsListUseCase
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.feature.gateway.NewsRepository
import com.example.cleanarchitecture.feature.gateway.local.NewsDatabase
import com.example.cleanarchitecture.feature.gateway.local.room.AppDatabase
import com.example.cleanarchitecture.feature.gateway.local.room.NewsRoomDatabase
import com.example.cleanarchitecture.feature.gateway.remote.NewsDataStore
import com.example.cleanarchitecture.feature.gateway.remote.api.CloudNewsDataStore
import com.example.cleanarchitecture.feature.gateway.remote.api.NewsApi
import com.example.cleanarchitecture.feature.ui.newslist.NewsListViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
object NewsModule {

    @NewsScope
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
            retrofit.create(NewsApi::class.java)

    @NewsScope
    @Provides
    fun provideCloudNewsDataStore(newsApi: NewsApi): NewsDataStore =
            CloudNewsDataStore(newsApi)

    @NewsScope
    @Provides
    fun provideNewsRoomDatabase(appDatabase: AppDatabase): NewsDatabase =
            NewsRoomDatabase(appDatabase)

    @NewsScope
    @Provides
    fun provideNewsRepositoryImpl(newsDataStore: NewsDataStore, newsDatabase: NewsDatabase): NewsRepositoryInterface =
            NewsRepository(newsDataStore, newsDatabase)

    @NewsScope
    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepositoryInterface): GetNewsListUseCase =
            GetNewsListUseCase(newsRepository)

    @NewsScope
    @Provides
    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
            NewsListViewModel(getNewsListUseCase)
}