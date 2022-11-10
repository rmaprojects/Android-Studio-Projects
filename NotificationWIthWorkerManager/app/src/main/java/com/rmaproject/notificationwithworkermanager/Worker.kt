package com.rmaproject.notificationwithworkermanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class Worker (context:Context, params:WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}
//    override fun doWork(): Result {
//        val id = inputData.getInt(NOTIFICATION_ID, 0)
//        sendNotification(id)
//
//        Log.d("id", id.toString())
//
//        return success()
//    }
//
//    private fun sendNotification(id:Int) {
//        val intent = Intent(applicationContext, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        intent.putExtra(NOTIFICATION_ID, id)
//
//        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val titleNotification = "Test Title"
//        val subtitleNotification = "Test subtitle"
//        val pendingIntent = getActivity(applicationContext, 0, intent, 0)
//
//        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_ID)
//            .setContentTitle(titleNotification).setSubText(subtitleNotification)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setDefaults(DEFAULT_ALL).setContentIntent(pendingIntent).setAutoCancel(true)
//
//        notification.priority = PRIORITY_MAX
//
//        if (SDK_INT >= O) {
//            notification.setChannelId(NOTIFICATION_ID)
//
//            val channel = NotificationChannel(NOTIFICATION_CHANNEL, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_HIGH)
//
//            channel.enableLights(true)
//            channel.lightColor = Color.RED
//
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        notificationManager.notify(id, notification.build())
//    }
//
//    companion object {
//        const val NOTIFICATION_ID = "appName_notification_id"
//        const val NOTIFICATION_NAME = "appName"
//        const val NOTIFICATION_CHANNEL = "appName_channel_01"
//        const val NOTIFICATION_WORK = "appName_notification_work"
//    }
//}