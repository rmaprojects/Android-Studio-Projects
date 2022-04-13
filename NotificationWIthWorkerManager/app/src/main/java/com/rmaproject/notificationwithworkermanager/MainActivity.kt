package com.rmaproject.notificationwithworkermanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var button:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)

//        button.setOnClickListener {
//            val timeNow = Calendar.getInstance().timeInMillis
//            val timeAfterThis = timeNow + TimeUnit.MINUTES.toMillis(1)
//            val delay = timeAfterThis - timeNow
//            val data = Data.Builder().putInt(NOTIFICATION_ID, 0).build()
//            scheduleNotification(delay, data)
//        }
    }

//    private fun scheduleNotification(delay: Long, data: Data) {
//        val notificationWork = OneTimeWorkRequest.Builder(Worker::class.java)
//            .setInitialDelay(delay, TimeUnit.MILLISECONDS).setInputData(data).build()
//
//        val instanceWorkManager = WorkManager.getInstance(this)
//        instanceWorkManager.beginUniqueWork(NOTIFICATION_WORK, ExistingWorkPolicy.REPLACE, notificationWork).enqueue()
//    }
}