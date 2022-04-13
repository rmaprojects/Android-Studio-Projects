package com.rmaproject.notificationreceiver

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.karn.notify.Notify

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
//        val i = Intent(context, MainActivity::class.java)

        intent!!.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

//        val alarmBuilder = NotificationCompat.Builder(context!!, "channelid")
//            .setSmallIcon(R.drawable.ic_launcher_background)
//            .setContentTitle("Alarm native nih boss")
//            .setContentText("Awokawowaokwakok")
//            .setAutoCancel(true)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setDefaults(NotificationCompat.DEFAULT_ALL)
//            .setContentIntent(pendingIntent)

        Notify.with(context!!).content {
            title = "Ini notifikasi"
            text = "Hello mamanks"
        }.meta {
            clickIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                0
            )
        }.show()

//        val notificationManager = NotificationManagerCompat.from(context)
//        notificationManager.notify(123, alarmBuilder.build())
    }
}