package com.example.cleanarchitecture.presentation

import android.app.Activity
import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.data.datastore.disk.db.AppDatabase
import com.example.cleanarchitecture.di.component.ApplicationComponent
import com.example.cleanarchitecture.di.component.DaggerApplicationComponent
import com.example.cleanarchitecture.di.module.ApplicationModule
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

class MyApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        val appDatabase = Room
                .databaseBuilder(
                        this,
                        AppDatabase::class.java,
                        BuildConfig.ROOM_DATABASE_NAME)
                .build()

        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(appDatabase))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}

val Activity.myApplication get() = application as MyApplication