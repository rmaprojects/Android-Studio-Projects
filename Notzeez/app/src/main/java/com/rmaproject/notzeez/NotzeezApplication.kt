package com.rmaproject.notzeez

import android.app.Application
import com.google.android.material.color.DynamicColors

class NotzeezApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}