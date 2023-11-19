package com.example.iseven

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.edit
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.iseven.data.repo.EvennessRepository
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App: Application(){
    companion object{
        const val NOTIFICATION_ID = "Notification_id"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        val sp = getSharedPreferences(NOTIFICATION_ID, Context.MODE_PRIVATE)
        if(sp.getInt(NOTIFICATION_ID, -1) == -1)
            sp.edit {
                putInt(NOTIFICATION_ID, 1)
            }
//        val myWorkRequest = PeriodicWorkRequestBuilder<FindOutEvennessWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS).build()
        val myWorkRequest = PeriodicWorkRequestBuilder<FindOutEvennessWorker>(1, TimeUnit.HOURS).build()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork("CheckNewNumber", ExistingPeriodicWorkPolicy.KEEP, myWorkRequest)
    }
    private fun createNotificationChannel() {
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
}