package com.sample.foregroundService

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationManagerCompat
import com.common.logging.TimberDebugTree
import com.common.logging.TimberReleaseTree
import timber.log.Timber

class ForegroundServiceApp : Application() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(TimberDebugTree())
        } else {
            Timber.plant(TimberReleaseTree())
        }

        val counterChannel = NotificationChannelCompat.Builder("counter_channel", NotificationManagerCompat.IMPORTANCE_DEFAULT)
            .setName("Counter")
            .build()

        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.createNotificationChannel(counterChannel)
    }

}