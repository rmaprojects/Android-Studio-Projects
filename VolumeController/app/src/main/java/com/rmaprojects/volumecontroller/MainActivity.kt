package com.rmaprojects.volumecontroller

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val volumeButton = findViewById<Button>(R.id.volumeButton)

        val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        volumeButton.setOnClickListener {
            audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
        }
    }

}