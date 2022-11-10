package com.rmaprojects.audioplayertester

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.view.isVisible
import com.arges.sepan.argmusicplayer.Models.ArgAudio
import com.arges.sepan.argmusicplayer.Models.ArgNotificationOptions
import com.arges.sepan.argmusicplayer.PlayerViews.ArgPlayerSmallView
import com.example.jean.jcplayer.view.JcPlayerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.tombolPlay)
//        val buttonJC = findViewById<Button>(R.id.jcplayButton)
//        val jcMusicPlayer = findViewById<JcPlayerView>(R.id.jcPlayer)
        val musicPlayer = findViewById<ArgPlayerSmallView>(R.id.argPlayerSmallView)
        val buttonSnow = findViewById<Button>(R.id.keSnow)

        val audio = ArgAudio.createFromURL("Misyari Rasyid Alafasy", "Al-Baqarah 1", "https://archive.org/download/quran-every-ayah/Mishary%20Rashid%20Alafasy.zip/002001.mp3")
        ArgNotificationOptions(this).isProgressEnabled = true
        ArgNotificationOptions(this).isEnabled = true
        musicPlayer.enableNotification(ArgNotificationOptions(this).setProgressEnabled(true))

        button.setOnClickListener {
            musicPlayer.isVisible = true
            musicPlayer.play(audio)
        }

        buttonSnow.setOnClickListener {
            startActivity(Intent(this, Snow::class.java))
        }

    }

}