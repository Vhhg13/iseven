package com.example.iseven

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.iseven.data.repo.EvennessRepository
import com.example.iseven.data.repo.KnownNumbersRepository
import dagger.hilt.android.HiltAndroidApp
import okhttp3.internal.notify
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App: Application(){
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val myWorkRequest = PeriodicWorkRequestBuilder<FindOutEvennessWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
            .build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("CheckNewNumber", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)
    }
    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        val name = getString(R.string.channel_name)
        val descriptionText = getString(R.string.channel_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel("1", name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system.
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    @Inject
    lateinit var eRepo: EvennessRepository
    @Inject
    lateinit var kRepo: KnownNumbersRepository
}