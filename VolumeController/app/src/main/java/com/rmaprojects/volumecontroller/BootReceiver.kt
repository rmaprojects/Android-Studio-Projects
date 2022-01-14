package com.rmaprojects.volumecontroller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.app.Notification
import android.app.PendingIntent

import androidx.core.app.NotificationCompat

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val inteent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, inteent, PendingIntent.FLAG_UPDATE_CURRENT)
        NotificationCompat.Builder(context!!)
            .setContentTitle("Adjust Volume")
            .setContentText("Klik untuk memunculkan volume")
            .setContentIntent(pendingIntent)
            .setPriority(Notification.PRIORITY_MAX)
            .build()
    }
}