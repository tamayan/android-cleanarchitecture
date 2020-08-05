package com.example.cleanarchitecture.presentation

import android.app.Activity
import android.app.Application
import com.example.cleanarchitecture.di.component.ActivityComponent
import com.example.cleanarchitecture.di.component.DaggerApplicationComponent
import com.example.cleanarchitecture.di.module.ApplicationModule
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

class MyApplication : Application() {

    val activityComponent: ActivityComponent by lazy {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule())
                .build()
                .activityComponent()
    }

    override fun onCreate() {
        super.onCreate()
//        Realm.init(applicationContext)
        Timber.plant(DebugTree())
    }
}

val Activity.myApplication get() = application as MyApplication