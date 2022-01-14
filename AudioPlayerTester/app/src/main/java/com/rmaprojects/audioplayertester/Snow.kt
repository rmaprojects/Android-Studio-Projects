package com.rmaprojects.audioplayertester

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import snow.player.PlayerClient
import snow.player.audio.MusicItem
import snow.player.playlist.Playlist

class Snow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snow)

        val playerButton = findViewById<Button>(R.id.playButton)
        val playerClient = PlayerClient.newInstance(this, MyPlayerService::class.java)
        playerClient.connect {
            Log.d("App:", "Connected: $it")
        }

        val playlist = createPlaylist()

        playerButton.setOnClickListener {
            playerClient.setPlaylist(playlist, true)
        }
    }

    private fun createPlaylist() : Playlist {
        val ayat1 = MusicItem.Builder()
            .setTitle("Al-Baqarah 1")
            .setArtist("Mishary Rashid Alafasy")
            .setUri("https://archive.org/download/quran-every-ayah/Mishary%20Rashid%20Alafasy.zip/002001.mp3")
            .build()

        return Playlist.Builder().append(ayat1).build()
    }
}