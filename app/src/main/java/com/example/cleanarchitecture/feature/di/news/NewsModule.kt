package com.example.cleanarchitecture.feature.di.news

import com.example.cleanarchitecture.feature.data.NewsRepository
import com.example.cleanarchitecture.feature.data.api.NewsApi
import com.example.cleanarchitecture.feature.data.api.NewsApiGateway
import com.example.cleanarchitecture.feature.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.feature.data.database.AppDatabase
import com.example.cleanarchitecture.feature.data.database.NewsDataStore
import com.example.cleanarchitecture.feature.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.feature.domain.application.GetNewsListUseCase
import com.example.cleanarchitecture.feature.domain.domain.news.NewsRepositoryInterface
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
    fun provideCloudNewsDataStore(newsApi: NewsApi): NewsApiGatewayInterface =
            NewsApiGateway(newsApi)

    @NewsScope
    @Provides
    fun provideNewsRoomDatabase(appDatabase: AppDatabase): NewsDataStoreInterface =
            NewsDataStore(appDatabase)

    @NewsScope
    @Provides
    fun provideNewsRepositoryImpl(apiGateway: NewsApiGatewayInterface, dataStore: NewsDataStoreInterface): NewsRepositoryInterface =
            NewsRepository(apiGateway, dataStore)

    @NewsScope
    @Provides
    fun provideGetNewsListUseCase(newsRepository: NewsRepositoryInterface): GetNewsListUseCase =
            GetNewsListUseCase(newsRepository)

    @NewsScope
    @Provides
    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
            NewsListViewModel(getNewsListUseCase)
}