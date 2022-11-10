package com.rmaproject.rehanwangsappapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val videoView = findViewById<VideoView>(R.id.videoView)

        videoView.setVideoURI(Uri.parse("android.resource://"
                + packageName + "/" + R.raw.rehanwangsap))

        button.setOnClickListener {
            button.isVisible = false
            videoView.isVisible = true
            videoView.start()
        }

        videoView.setOnCompletionListener {
            button.isVisible = true
            videoView.isVisible = false
        }
    }
}