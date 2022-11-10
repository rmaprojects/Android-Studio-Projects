package com.rmaproject.notificationreceiver

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateUtils.FORMAT_24HOUR
import android.widget.Button
import android.widget.Toast
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.MaterialTimePicker.INPUT_MODE_CLOCK
import com.google.android.material.timepicker.TimeFormat
import io.karn.notify.Notify
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        Notify.defaultConfig {
            header {
                icon = R.drawable.ic_launcher_foreground
            }
            alerting("KEYALERT") {
                channelName = "NAMECHANERL"
            }
        }

        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            Notify.with(this).content {
                title = "Ini notifikasi"
                text = "Hello mamanks"
            }.meta {
                clickIntent = PendingIntent.getActivity(
                    this@MainActivity,
                    0,
                    Intent(this@MainActivity, MainActivity::class.java),
                    0
                )
            }.show()
        }

        val nativeButton = findViewById<Button>(R.id.button2)

        setAlarm()
    }

    private fun createNotificationChannel() {

        //Create notification channel for android O and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "idChannel"
            val descriptionText = "channel thest alarm"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("channelid", name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun setAlarm() {

        calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 12
        calendar[Calendar.MINUTE] = 10
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }
}