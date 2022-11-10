package com.rmaproject.submissionandroidbeginnerdicoding.ui.pages

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.submissionandroidbeginnerdicoding.R
import com.rmaproject.submissionandroidbeginnerdicoding.databinding.ActivityProfileBinding


class ProfilePage : AppCompatActivity() {

    private val binding by viewBinding<ActivityProfileBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        binding.toolBar.setNavigationOnClickListener {
            finish()
        }

        binding.btnToMyGithub.setOnClickListener {
            goToMyGithub()
        }
    }

    private fun goToMyGithub() {
        startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.github.com/RMA-17")))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}