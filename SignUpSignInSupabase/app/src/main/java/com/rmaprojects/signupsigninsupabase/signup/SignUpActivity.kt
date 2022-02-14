package com.rmaprojects.signupsigninsupabase.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.signupsigninsupabase.MainActivity
import com.rmaprojects.signupsigninsupabase.R
import com.rmaprojects.signupsigninsupabase.TinyDB
import com.rmaprojects.signupsigninsupabase.databinding.ActivitySignUpBinding
import com.rmaprojects.signupsigninsupabase.model.postmodel.signin.SignInPost
import com.rmaprojects.signupsigninsupabase.model.postmodel.signup.SignUpPost
import com.rmaprojects.signupsigninsupabase.model.returnmodel.signup.SignUpReturn
import com.rmaprojects.signupsigninsupabase.services.ApiServices
import com.rmaprojects.signupsigninsupabase.signin.SignInActivity
import kotlinx.coroutines.launch

class SignUpActivity : AppCompatActivity() {
    private val binding: ActivitySignUpBinding by viewBinding()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val email = binding.edtTxtEmail.editText?.text
        val passwrod = binding.edtTxtPassword.editText?.text
        val api = ApiServices.create()

        binding.btnSignUp.setOnClickListener {
            lifecycleScope.launch {
                try {
                    api.signUp(SignUpPost(email.toString(), passwrod.toString()))
                } catch (e:Exception) {
                    Snackbar.make(binding.constraintLayout, "Email gagal didaftarkan!", Snackbar.LENGTH_SHORT)
                        .show()
                } finally {
                    Snackbar.make(binding.constraintLayout, "Email Berhasil didaftarkan!", Snackbar.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@SignUpActivity, SignInActivity::class.java))
                    finish()
                }
            }
        }

        binding.btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    }
}