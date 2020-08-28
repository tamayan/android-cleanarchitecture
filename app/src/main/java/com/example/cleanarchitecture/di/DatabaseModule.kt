package com.example.cleanarchitecture.di

import android.content.Context
import androidx.room.Room
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.database.AppDatabase
import com.example.cleanarchitecture.data.database.NewsDataStore
import com.example.cleanarchitecture.data.database.NewsDataStoreInterface
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
    fun provideRoom(@ApplicationContext context: Context): AppDatabase =
            Room
                    .databaseBuilder(
                            context,
                            AppDatabase::class.java,
                            BuildConfig.ROOM_DATABASE_NAME)
                    .build()

    @Singleton
    @Provides
    fun provideNewsRoomDatabase(appDatabase: AppDatabase): NewsDataStoreInterface =
            NewsDataStore(appDatabase)
}