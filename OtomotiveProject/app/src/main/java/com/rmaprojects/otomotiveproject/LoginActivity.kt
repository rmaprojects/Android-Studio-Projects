package com.rmaprojects.otomotiveproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.color.MaterialColors
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.otomotiveproject.databinding.ActivityLoginBinding
import com.rmaprojects.otomotiveproject.services.ApiServices
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private val binding:ActivityLoginBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginAPI = ApiServices.create()

        val noAnggotaInput = binding.noAnggotaEdtText
        val passwordInput = binding.passwordEdtText

        binding.loginButton.isEnabled =
            !(noAnggotaInput.editText?.text.toString() == "" && passwordInput.editText?.text.toString() == "")
        
        binding.loginButton.setOnClickListener {
            if (noAnggotaInput.error == null && passwordInput.error == null) {
                lifecycleScope.launch {
                    val response = loginAPI.userLogin(noAnggotaInput.editText?.text.toString(),
                        passwordInput.editText?.text.toString())
                    if (response.status) {
                        if (response.isAdmin) {
                            startActivity(Intent(this@LoginActivity, DashboardAdminActivity::class.java))
                        } else if (!response.isAdmin) {
                            startActivity(Intent(this@LoginActivity, DashboardCustomerActivity::class.java))
                        }
                    } else {
                        Snackbar.make(ConstraintLayout(this@LoginActivity), "Login Gagal, Username atau Password salah!", Snackbar.LENGTH_SHORT)
                            .setTextColor(MaterialColors.getColor(ConstraintLayout(this@LoginActivity), R.attr.colorPrimary))
                            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
                            .setAction("Ok") {
                                // Kalau di kotlin ini tidak usah di isi untuk dismiss saja.
                            }
                            .setActionTextColor(MaterialColors.getColor(ConstraintLayout(this@LoginActivity), R.attr.colorPrimary))
                            .show()
                    }
                }
            } else {
                Toast.makeText(this, "Masih Ada Error, coba lagi!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}