package com.rmaprojects.submission1.pages.auth

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.databinding.FragmentRegisterBinding
import com.rmaprojects.submission1.utils.showSnackbar

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()
    private val viewModel : AuthViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.explode)
        reenterTransition = inflater.inflateTransition(android.R.transition.explode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRegister.setOnClickListener {
            val name = binding.edRegisterName.text.toString()
            val email = binding.edRegisterEmail.text.toString()
            val password = binding.edRegisterPassword.editText?.text.toString()

            if (name.isEmpty()) {
                binding.nameInputLayout.error = getString(R.string.err_field_empty)
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                binding.emailInputLayout.error = getString(R.string.err_field_empty)
                return@setOnClickListener
            }

            registerUser(name, email, password)
        }

        viewModel.isRegisterLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingListener(isLoading)
        }
    }

    private fun loadingListener(loading: Boolean?) {
        if (loading == null) {
            binding.btnRegister.isEnabled = true
            binding.progressCircular.isVisible = false
            showSnackbar(binding.root, getString(R.string.alert_register_fail), Snackbar.LENGTH_SHORT)
            return
        }

        if (loading) {
            binding.btnRegister.isEnabled = false
            binding.progressCircular.isVisible = true
        } else {
            binding.btnRegister.isEnabled = true
            binding.progressCircular.isEnabled = false
        }
    }

    private fun registerUser(name: String, email: String, password: String) {
        viewModel.registerUser(name, email, password)

        viewModel.isRegisterSuccess.observe(viewLifecycleOwner) { isSuccess ->
            if (!isSuccess) {
                showSnackbar(binding.root, getString(R.string.alert_register_fail), Snackbar.LENGTH_SHORT)
                return@observe
            }

            showSnackbar(binding.root, getString(R.string.alert_register_success), Snackbar.LENGTH_SHORT)
            findNavController().navigateUp()
        }
    }
}