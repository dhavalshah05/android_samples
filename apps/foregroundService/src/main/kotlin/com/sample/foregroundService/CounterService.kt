package com.sample.foregroundService

import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.common.logging.MyLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CounterService : Service() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private var isStarted = false

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        stop()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        start()
        return START_STICKY
    }

    private fun start() {
        if (isStarted) {
            MyLogger.d("start() - service is already running")
            return
        }

        scope.launch {
            Counter.currentValue.collectLatest { value ->
                MyLogger.d("#service", "Counter: $value")
                showNotification(value)
            }
        }

        MyLogger.d("#service", "starting service")
        isStarted = true
        Counter.startTimer()
    }

    private fun stop() {
        Counter.stopCounter()
        scope.cancel()
        stopSelf()
        isStarted = false
        MyLogger.d("#service", "service stopped")
    }

    private fun showNotification(counterValue: Int) {
        val intent = Intent(this, ForegroundServiceActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification = NotificationCompat
            .Builder(this, "counter_channel")
            .setSmallIcon(com.fynd.nitrozen.R.drawable.ic_nit_home)
            .setContentTitle("Counter")
            .setContentText("Value: $counterValue")
            .setStyle(NotificationCompat.BigTextStyle())
            .setContentIntent(pendingIntent)
            .build()

        startForeground(1, notification)
    }

    companion object {
        fun startService(context: Context) {
            val intent = Intent(context, CounterService::class.java)
            if (Build.VERSION.SDK_INT < 26) {
                context.startService(intent)
            } else {
                context.startForegroundService(intent)
            }
        }

        fun stopService(context: Context) {
            val intent = Intent(context, CounterService::class.java)
            context.stopService(intent)
        }
    }
}