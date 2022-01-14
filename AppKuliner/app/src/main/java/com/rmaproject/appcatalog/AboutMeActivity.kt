package com.rmaproject.appcatalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.appcatalog.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    private val binding: ActivityAboutMeBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        binding.topAppBar.setNavigationOnClickListener {
            startActivity(Intent(this, KatalogActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, KatalogActivity::class.java))
        finish()
    }
}