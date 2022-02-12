package com.rmaproject.timepicker

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat.CLOCK_24H
import com.rmaproject.timepicker.data.AlarmDatabase
import com.rmaproject.timepicker.model.AlarmModel
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

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
        val database = AlarmDatabase.getInstance(this)
        button.setOnClickListener {
            materialTimePicker.show(supportFragmentManager, "MainActivity")
            materialTimePicker.addOnPositiveButtonClickListener {
                val jam = converterToZero(materialTimePicker.hour)
                val menit = converterToZero(materialTimePicker.minute)
                lifecycleScope.launch {
                    database.alarmDatabase().simpanAlarm(AlarmModel(null, "Test", jam, menit))
                }
                database.alarmDatabase().getAlarm(1).asLiveData().observe(this) {
                    clockText.text = "${it[0].hour}:${it[0].min}"
                }
            }

        }
    }

    private fun converterToZero(number: Int): String {

        val formatteNumber: String = if (number <= 9) {
            "0$number"
        } else {
            number.toString()
        }

        return formatteNumber
    }
}