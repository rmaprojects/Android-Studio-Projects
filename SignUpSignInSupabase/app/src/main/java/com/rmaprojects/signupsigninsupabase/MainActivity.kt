package com.rmaprojects.signupsigninsupabase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.signupsigninsupabase.databinding.ActivityMainBinding
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signin.SignInReturn
import com.rmaprojects.signupsigninsupabase.preferences.SignedInPreferences
import com.rmaprojects.signupsigninsupabase.signin.SignInActivity

class MainActivity : AppCompatActivity() {
    private val binding:ActivityMainBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val getCredential = TinyDB(this).getObject(SignInActivity.KEYSIGNIN, SignInReturn::class.java) ?: null
        if (getCredential != null) {
            binding.txtEmail.text = getCredential.user.email
        }

        binding.logoutbutton.setOnClickListener {
            SignedInPreferences(this).isSignedIn = false
            TinyDB(this).clear()
            startActivity(Intent(this, SplashScreenActivity::class.java))
            finish()
        }
    }
}