package com.rmaproject.testpopup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.github.zawadz88.materialpopupmenu.popupMenu
import com.rmaproject.testpopup.R.drawable.ic_baseline_airline_seat_individual_suite_24
import com.skydoves.powermenu.MenuAnimation
import com.skydoves.powermenu.OnMenuItemClickListener
import com.skydoves.powermenu.PowerMenu
import com.skydoves.powermenu.PowerMenuItem
import com.skydoves.powermenu.kotlin.powerMenu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val popupButton = findViewById<Button>(R.id.popupButton)
        val powerButton = findViewById<Button>(R.id.powerMenuButton)

        popupButton.setOnClickListener {
            showPopUpMenu()
        }

        powerButton.setOnClickListener {
            showPowerMenu()
        }
    }

    private fun showPopUpMenu() {
        val popupMenu = popupMenu {
            style = com.github.zawadz88.materialpopupmenu.R.style.Widget_MPM_Menu
            section {
                item {
                    label = "Test mamank"
                    icon = ic_baseline_airline_seat_individual_suite_24
                    callback = { //callback ini sama seperti setOnClickListener {}
                        Toast.makeText(this@MainActivity, "Test mamank", Toast.LENGTH_SHORT).show()
                    }
                }
                item {
                    //labelRes = sama seperti label, tetapi diambil dari resource
                    label = "Test mamank"
                    icon = ic_baseline_airline_seat_individual_suite_24
                    callback = { //callback ini sama seperti setOnClickListener {}
                        Toast.makeText(this@MainActivity, "Test 2 maamnk", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        popupMenu.show(this, findViewById(R.id.popupButton))
    }

    private fun showPowerMenu() {
        PowerMenu.Builder(this)
            .addItem(PowerMenuItem("Testing mamang", ic_baseline_airline_seat_individual_suite_24, false)).setOnMenuItemClickListener { position, item ->
                Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show()
            }
            .addItem(PowerMenuItem("Testing mamang2", ic_baseline_airline_seat_individual_suite_24, false)).setOnMenuItemClickListener { position, item ->
                Toast.makeText(this, "Test2", Toast.LENGTH_SHORT).show()
            }
            .setAnimation(MenuAnimation.FADE)
            .build()
            .showAsAnchorLeftTop(findViewById(R.id.powerMenuButton))
    }
}