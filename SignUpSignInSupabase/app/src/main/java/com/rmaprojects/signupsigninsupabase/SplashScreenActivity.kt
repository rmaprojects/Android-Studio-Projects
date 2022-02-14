package com.rmaprojects.signupsigninsupabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signin.SignInReturn
import com.rmaprojects.signupsigninsupabase.preferences.SignedInPreferences
import com.rmaprojects.signupsigninsupabase.signin.SignInActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        if (!SignedInPreferences(this).isSignedIn) {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}