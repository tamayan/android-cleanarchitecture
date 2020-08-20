package com.example.cleanarchitecture.feature.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.feature.data.database.AppDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Module
object ApplicationModule {

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
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDatabase =
            Room
                    .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            BuildConfig.ROOM_DATABASE_NAME)
                    .build()
}