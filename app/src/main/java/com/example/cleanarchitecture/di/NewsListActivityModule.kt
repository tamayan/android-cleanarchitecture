package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.NewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.CloudNewsDataStore
import com.example.cleanarchitecture.data.datastore.cloud.client.NewsApiClient
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.NewsRoomDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.datastore.disk.db.NewsDatabase
import com.example.cleanarchitecture.data.repository.NewsRepositoryImpl
import com.example.cleanarchitecture.domain.repository.NewsRepository
import com.example.cleanarchitecture.domain.usecase.GetNewsListUseCase
import com.example.cleanarchitecture.presentation.newslist.NewsListViewModel
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by tamayan on 2018/02/02.
 */

@Module(includes = [NewsListActivityModuleBinds::class])
object NewsListActivityModule {

    @Singleton
    @Provides
    fun provideNewsApiClient(moshi: Moshi, basicCredentialProvider: BasicCredentialProvider): NewsApiClient =
            NewsApiClient(moshi, BuildConfig.BASE_URL, basicCredentialProvider)

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class CloudNewsDataStore

    @CloudNewsDataStore
    @Singleton
    @Provides
    fun provideCloudNewsDataStore(newsApiClient: NewsApiClient): NewsDataStore =
            CloudNewsDataStore(newsApiClient)

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class NewsRoomDatabase

    @NewsRoomDatabase
    @Singleton
    @Provides
    fun provideNewsDatabase(appDatabase: AppDatabase): NewsDatabase =
            NewsRoomDatabase(appDatabase)

    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepository): GetNewsListUseCase =
            GetNewsListUseCase(newsRepository)

    @Provides
    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
            NewsListViewModel(getNewsListUseCase)
}

@Module
abstract class NewsListActivityModuleBinds {

    @Singleton
    @Binds
    abstract fun bindNewsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository
}