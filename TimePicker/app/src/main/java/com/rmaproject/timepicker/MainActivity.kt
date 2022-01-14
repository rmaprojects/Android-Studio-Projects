package com.rmaproject.timepicker

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.timeShower)
        val materialTimePicker = MaterialTimePicker.Builder()
            .setTimeFormat(CLOCK_24H)
            .setHour(12)
            .setMinute(0)
            .setTitleText("Set Alarm")
            .build()
        val clockText = findViewById<TextView>(R.id.jamAlarm)

        button.setOnClickListener {
            materialTimePicker.show(supportFragmentManager, "MainActivity")
            materialTimePicker.addOnPositiveButtonClickListener {
                val jam = materialTimePicker.hour
                val menit = materialTimePicker.minute
                clockText.text = "$jam:$menit"
                val intent = Intent(AlarmClock.ACTION_SET_ALARM)
                intent.putExtra(AlarmClock.EXTRA_HOUR, jam)
                intent.putExtra(AlarmClock.EXTRA_MINUTES, menit)
                startActivity(intent)
            }
        }
    }
}