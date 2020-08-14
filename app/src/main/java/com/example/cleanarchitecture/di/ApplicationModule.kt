package com.example.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.cloud.client.okhttp.BasicCredentialProvider
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.data.repository.BasicCredentialProviderImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Singleton
    @Provides
    fun provideBasicCredentialProvider(): BasicCredentialProvider =
            BasicCredentialProviderImpl()

    @Singleton
    @Provides
    fun provideRoom(context: Context): AppDatabase {
        return Room
                .databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        BuildConfig.ROOM_DATABASE_NAME)
                .build()
    }
}