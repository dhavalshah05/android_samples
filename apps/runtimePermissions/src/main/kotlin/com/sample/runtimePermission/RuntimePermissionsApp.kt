package com.sample.runtimePermission

import android.app.Application
import com.common.logging.TimberDebugTree
import com.common.logging.TimberReleaseTree
import com.sample.runtimePermissions.BuildConfig
import timber.log.Timber

class RuntimePermissionsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }
    }

}