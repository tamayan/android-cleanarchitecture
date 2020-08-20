package com.example.cleanarchitecture.di.news

import com.example.cleanarchitecture.data.NewsRepository
import com.example.cleanarchitecture.data.api.NewsApi
import com.example.cleanarchitecture.data.api.NewsApiGateway
import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.example.cleanarchitecture.data.database.AppDatabase
import com.example.cleanarchitecture.data.database.NewsDataStore
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
import com.example.cleanarchitecture.domain.application.news.GetNewsListInteractor
import com.example.cleanarchitecture.domain.domain.news.NewsRepositoryInterface
import com.example.cleanarchitecture.ui.news.list.NewsListViewModel
import com.example.cleanarchitecture.usecase.news.list.GetNewsListUseCase
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
            GetNewsListInteractor(newsRepository)

    @NewsScope
    @Provides
    fun provideNewsListViewModel(getNewsListUseCase: GetNewsListUseCase): NewsListViewModel =
            NewsListViewModel(getNewsListUseCase)
}