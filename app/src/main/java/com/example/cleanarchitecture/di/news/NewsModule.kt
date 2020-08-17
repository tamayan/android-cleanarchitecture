package com.example.cleanarchitecture.di.news

import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.CloudNewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.NewsApi
import com.example.cleanarchitecture.data.datastore.disk.NewsRoomDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDatabase
import com.example.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.example.cleanarchitecture.presentation.newslist.NewsListViewModel
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