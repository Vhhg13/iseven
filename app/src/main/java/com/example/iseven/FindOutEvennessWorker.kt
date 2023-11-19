package com.example.iseven

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.edit
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class FindOutEvennessWorker(
    appContext: Context,
    val params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {
    val eRepo = (appContext as App).eRepo
    override suspend fun doWork(): Result {
        val sp = applicationContext.getSharedPreferences(App.NOTIFICATION_ID, Context.MODE_PRIVATE)
        val id = sp.getInt(App.NOTIFICATION_ID, 42)

        val number = (0..20000).shuffled()[0]
        val isEven = eRepo.isEven(number)

        val builder = NotificationCompat.Builder(applicationContext, "1").setSmallIcon(R.mipmap.ic_launcher_foreground).setContentTitle("Новое число!").setContentText("Узнайте, чётное ли число $number!!! ($id)").setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(applicationContext)){
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(id, builder.build())
                sp.edit {
                    putInt(App.NOTIFICATION_ID, id+1)
                }
            }
        }
        Log.i("WORKA", isEven.toString() + id.toString())

        return Result.success()
    }
}