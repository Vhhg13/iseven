package com.example.iseven

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

class FindOutEvennessWorker(
    appContext: Context,
    val params: WorkerParameters
) :
    CoroutineWorker(appContext, params) {
    val eRepo = (appContext as App).eRepo
    val kRepo = (appContext as App).kRepo
    private var id = 1
    override suspend fun doWork(): Result {



        val number = (0..20000).shuffled()[0]
        val isEven = eRepo.isEven(number)
        kRepo.submit(isEven)

        val builder = NotificationCompat.Builder(applicationContext, "1").setSmallIcon(R.mipmap.ic_launcher_foreground).setContentTitle("Новое число!").setContentText("Узнайте, чётное ли число $number!!!").setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(applicationContext)){
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                notify(++id, builder.build())
            }
        }
        Log.i("WORKA", isEven.toString() + id.toString())

        return Result.success()
    }
}