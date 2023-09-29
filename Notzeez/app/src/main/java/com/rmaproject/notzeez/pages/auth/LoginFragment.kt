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
import com.rmaproject.notzeez.databinding.FragmentLoginBinding
import com.rmaproject.notzeez.factory.ViewModelFactory
import com.rmaproject.notzeez.util.Status.*
import com.rmaproject.notzeez.util.createSnackBar

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_nav_login_to_nav_register)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.inputEmail.editText?.text.toString()
            val password = binding.inputPassword.editText?.text.toString()
            if (email.isEmpty()) {
                binding.inputEmail.error = getString(R.string.msg_err_mail_empty)
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                binding.inputPassword.error = getString(R.string.msg_err_pw_empty)
                return@setOnClickListener
            }
            viewModel.loginWithMailAndPassword(email, password)
        }

        viewModel.userLoginStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Loading -> {
                    binding.progressCircular.isVisible = true
                    binding.btnLogin.isEnabled = false
                }
                is Error -> {
                    binding.progressCircular.isVisible = false
                    binding.btnLogin.isEnabled = true
                    Log.d("ERROR", status.message.toString())
                }
                is Success -> {
                    binding.progressCircular.isVisible = false
                    binding.btnLogin.isEnabled = true
                    createSnackBar(
                        requireActivity().findViewById(R.id.root),
                        getString(R.string.msg_success_login),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_nav_login_to_nav_dashboard)
                }
            }
        }
    }
}