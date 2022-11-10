package com.rmaprojects.signupsigninsupabase.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.signupsigninsupabase.MainActivity
import com.rmaprojects.signupsigninsupabase.R
import com.rmaprojects.signupsigninsupabase.TinyDB
import com.rmaprojects.signupsigninsupabase.databinding.ActivitySignInBinding
import com.rmaprojects.signupsigninsupabase.model.postmodel.signin.SignInPost
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signin.SignInReturn
import com.rmaprojects.signupsigninsupabase.preferences.SignedInPreferences
import com.rmaprojects.signupsigninsupabase.services.ApiServices
import com.rmaprojects.signupsigninsupabase.signup.SignUpActivity
import kotlinx.coroutines.launch

class SignInActivity : AppCompatActivity() {
    private val binding : ActivitySignInBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val email = binding.edtTxtEmail.editText?.text
        val password = binding.edtTxtPassword.editText?.text
        val api = ApiServices.create()

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
            finish()
        }

        binding.btnSignIn.setOnClickListener {
            Log.d("INPUTAN", "$email, $password")
            lifecycleScope.launch {
                val response = api.signIn("password", SignInPost(email.toString(), password.toString()))
                try {
                    Snackbar.make(binding.constraintLayout, "Login Berhasil!", Snackbar.LENGTH_SHORT)
                        .setAction("Ok") {}
                        .show()
                    TinyDB(this@SignInActivity).putObject(
                        KEYSIGNIN, SignInReturn(
                            response.access_token,
                            response.expires_in,
                            response.refresh_token,
                            response.token_type,
                            response.user
                        ))
                    SignedInPreferences(this@SignInActivity).isSignedIn = true
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                } catch (e:Exception) {
                    Snackbar.make(binding.constraintLayout, "User credential salah! Silahkan perbaiki lalu coba lagi!", Snackbar.LENGTH_SHORT)
                        .setAction("Ok") {}
                        .show()
                }
            }
        }
    }

    companion object {
        const val KEYSIGNIN = "SIGNINKEY"
    }
}