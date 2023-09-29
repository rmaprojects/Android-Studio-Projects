package com.rmaprojects.rmacam

import android.app.Application
import com.google.firebase.FirebaseApp

class RMACamApp: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}