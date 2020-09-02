package com.example.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.database.AppDatabase
import com.example.cleanarchitecture.data.database.VideoDataStore
import com.example.cleanarchitecture.data.database.VideoDataStoreInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
            Room
                    .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            BuildConfig.ROOM_DATABASE_NAME)
                    .build()

    @Singleton
    @Provides
    fun provideVideoDataStore(appDatabase: AppDatabase): VideoDataStoreInterface =
            VideoDataStore(appDatabase)
}