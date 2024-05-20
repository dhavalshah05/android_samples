package com.sample.foregroundService

import android.app.Application
import com.common.logging.TimberDebugTree
import com.common.logging.TimberReleaseTree
import timber.log.Timber

class ForegroundServiceApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }

}