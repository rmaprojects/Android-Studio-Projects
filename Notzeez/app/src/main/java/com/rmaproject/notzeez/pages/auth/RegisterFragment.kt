package com.rmaproject.notzeez.pages.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentRegisterBinding
import com.rmaproject.notzeez.factory.ViewModelFactory
import com.rmaproject.notzeez.util.Status.*
import com.rmaproject.notzeez.util.createSnackBar

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.inputEmail.editText?.text.toString()
            val password = binding.inputPassword.editText?.text.toString()
            val username = binding.inputUsername.editText?.text.toString()
            if (email.isEmpty()) {
                binding.inputEmail.error = getString(R.string.msg_err_mail_empty)
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.inputEmail.error = ""
                return@setOnClickListener
            }
            if (username.isEmpty()) {
                binding.inputEmail.error = ""
                return@setOnClickListener
            }
            viewModel.signUpWithMailAndPassword(username, email, password)
        }

        viewModel.userRegistrationStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Loading -> {
                    binding.progressCircular.isVisible = true
                    binding.btnRegister.isEnabled = false
                }
                is Error -> {
                    binding.progressCircular.isVisible = false
                    binding.btnRegister.isEnabled = true
                    createSnackBar(
                        binding.root,
                        status.message.toString(),
                        Snackbar.LENGTH_SHORT
                    ).setAction("Ok") {}.show()
                    Log.d("ERROR", status.message.toString())
                }
                is Success -> {
                    binding.progressCircular.isVisible = false
                    binding.btnRegister.isEnabled = true
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_success_register),
                        Snackbar.LENGTH_LONG
                    ).setAction(getString(R.string.msg_back_to_login)) {
                        findNavController().navigateUp()
                    }.show()
                }
            }
        }
    }
}