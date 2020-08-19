package com.example.cleanarchitecture.feature.di.news

import com.example.cleanarchitecture.feature.domain.application.GetNewsListUseCase
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepository
import com.example.cleanarchitecture.feature.infrastructure.NewsRepositoryImpl
import com.example.cleanarchitecture.feature.infrastructure.local.NewsDatabase
import com.example.cleanarchitecture.feature.infrastructure.local.room.AppDatabase
import com.example.cleanarchitecture.feature.infrastructure.local.room.NewsRoomDatabase
import com.example.cleanarchitecture.feature.infrastructure.remote.NewsDataStore
import com.example.cleanarchitecture.feature.infrastructure.remote.retrofit.CloudNewsDataStore
import com.example.cleanarchitecture.feature.infrastructure.remote.retrofit.NewsApi
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
    fun provideNewsRepositoryImpl(newsDataStore: NewsDataStore, newsDatabase: NewsDatabase): NewsRepository =
            NewsRepositoryImpl(newsDataStore, newsDatabase)

    @NewsScope
    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepository): GetNewsListUseCase =
            GetNewsListUseCase(newsRepository)

    @NewsScope
    @Provides
    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
            NewsListViewModel(getNewsListUseCase)
}