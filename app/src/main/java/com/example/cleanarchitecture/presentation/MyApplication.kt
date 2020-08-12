package com.example.cleanarchitecture.presentation

import android.app.Activity
import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * Created by MSnowRobin016 on 2017/12/28.
 */

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}

val Activity.myApplication get() = application as MyApplication