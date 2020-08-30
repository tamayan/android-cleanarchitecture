package com.example.cleanarchitecture.di

import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.api.NewsApi
import com.example.cleanarchitecture.data.api.NewsApiGateway
import com.example.cleanarchitecture.data.api.NewsApiGatewayInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient =
            OkHttpClient
                    .Builder()
                    .addNetworkInterceptor { chain ->
                        val request = chain
                                .request()
                                .newBuilder()
                                .addHeader("Authorization", Credentials.basic(BuildConfig.BASIC_USER_NAME, BuildConfig.BASIC_PASS))
                                .build()
                        chain.proceed(request)
                    }
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .build()

    @Singleton
    @Provides
    fun provideMoshi(): Moshi =
            Moshi
                    .Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit =
            Retrofit
                    .Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()

    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi =
            retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideCloudNewsDataStore(newsApi: NewsApi): NewsApiGatewayInterface =
            NewsApiGateway(newsApi)
}