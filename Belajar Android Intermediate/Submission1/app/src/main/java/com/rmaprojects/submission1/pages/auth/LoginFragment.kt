package com.rmaprojects.submission1.pages.auth

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.databinding.FragmentLoginBinding
import com.rmaprojects.submission1.showSnackbar

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnLogin.setOnClickListener {
                val email = edLoginEmail.text.toString()
                val password = edLoginPassword.text.toString()

                if (email.isEmpty()) {
                    binding.inputEmail.error = getString(R.string.err_field_empty)
                    return@setOnClickListener
                }

                if (password.isEmpty()) {
                    binding.inputEmail.error = getString(R.string.err_field_empty)
                    return@setOnClickListener
                }

                login(email, password)
            }

            btnRegister.setOnClickListener {
                findNavController().navigate(R.id.action_nav_login_to_nav_register)
            }

            viewModel.isLoginLoading.observe(viewLifecycleOwner) { isLoading ->
                loadingListener(isLoading)
            }
        }
    }

    private fun loadingListener(isLoading: Boolean?) = with(binding) {
        if (isLoading == null) {
            progressIndicator.isVisible = false
            btnLogin.isEnabled = true
            showSnackbar(root, getString(R.string.alert_login_fail), Snackbar.LENGTH_SHORT)
            return@with
        }

        if (isLoading) {
            progressIndicator.isVisible = true
            btnLogin.isEnabled = false
        } else {
            progressIndicator.isVisible = false
            btnLogin.isEnabled = true
        }
    }

    private fun login(email: String, password: String) {
        viewModel.loginUser(email, password)
        viewModel.isLoginSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (!isSuccess) {
                showSnackbar(
                    binding.root,
                    getString(R.string.alert_login_fail),
                    Snackbar.LENGTH_SHORT
                )
            } else {
                findNavController().navigate(R.id.action_nav_login_to_nav_list_story)
            }
        }
    }
}
