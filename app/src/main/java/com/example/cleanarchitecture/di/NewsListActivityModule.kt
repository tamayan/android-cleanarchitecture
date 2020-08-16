package com.example.cleanarchitecture.di

import dagger.Module

/**
 * Created by tamayan on 2018/02/02.
 */

@Module
object NewsListActivityModule {

//    @Singleton
//    @Provides
//    fun provideNewsDataStore(newsApiClient: NewsApiClient): NewsDataStore =
//            CloudNewsDataStore(newsApiClient)

//    @Singleton
//    @Provides
//    fun provideNewsRoomDatabase(database: AppDatabase): NewsDatabase =
//            NewsRoomDatabase(database, database.newsDao())

//    @Provides
//    fun provideNewsRepository(newsDataStore: NewsDataStore, newsDatabase: NewsDatabase): NewsRepository =
//            NewsRepositoryImpl(newsDataStore, newsDatabase)

//    @Provides
//    fun provideGetNewsListUseCase(newsRepository: NewsRepository): GetNewsListUseCase =
//            GetNewsListUseCase(newsRepository)
//
//    @Provides
//    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
//            NewsListViewModel(getNewsListUseCase)
}