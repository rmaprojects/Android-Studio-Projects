package com.rmaproject.notificationreceiver

import android.app.PendingIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.karn.notify.Notify

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Notify.defaultConfig {
            header {
                icon = R.drawable.ic_launcher_foreground
            }
            alerting("KEYALERT") {
                channelName = "NAMECHANERL"
            }
        }

        val button:Button = findViewById(R.id.button)

        button.setOnClickListener {
            Notify.with(this).content {
                title = "Ini notifikasi"
                text = "Hello mamanks"
            }.meta {
                clickIntent = PendingIntent.getActivity(this@MainActivity, 0, Intent(this@MainActivity, MainActivity::class.java), 0)
            }.show()
        }
    }
}