package com.rmaproject.notzeez.pages.profile

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.rmaproject.notzeez.R
import com.rmaproject.notzeez.databinding.FragmentProfileBinding
import com.rmaproject.notzeez.factory.ViewModelFactory
import com.rmaproject.notzeez.pages.dashboard.DashboardViewModel
import com.rmaproject.notzeez.util.Status
import com.rmaproject.notzeez.util.createAlertDialog
import com.rmaproject.notzeez.util.createSnackBar

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding: FragmentProfileBinding by viewBinding()
    private val viewModel: DashboardViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userData.observe(viewLifecycleOwner) { user ->
            binding.txtUsername.text = user?.username
            binding.profileIcon.load(
                if (user?.profileUrl.isNullOrEmpty()) R.drawable.ic_baseline_account_circle_24
                else user?.profileUrl
            )
        }

        binding.profileIcon.setOnClickListener {
            launchImagePicker()
        }

        binding.btnSignOut.setOnClickListener {
            createAlertDialog(
                requireContext(),
                getString(R.string.msg_warn_sign_out),
                getString(R.string.msg_warn_message_sign_out)
            ).setPositiveButton(getString(R.string.decision_yes)) { _, _ ->
                Firebase.auth.signOut()
                profileViewModel.deleteAllNotes()
                findNavController().navigate(R.id.action_nav_profile_to_nav_login)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.msg_success_log_out),
                    Toast.LENGTH_SHORT
                ).show()
            }.setNegativeButton(getString(R.string.decision_no)) { _, _ -> }
                .setIcon(R.drawable.ic_baseline_logout_24).create().show()
        }

        profileViewModel.uploadImageStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Error -> {
                    binding.progressCircular.isVisible = false
                    Log.d("ERROR", status.message.toString())
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.msg_failed_upload_img),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Status.Loading -> {
                    binding.progressCircular.isVisible = true
                    createSnackBar(binding.root, getString(R.string.msg_upload_image), Snackbar.LENGTH_SHORT).show()
                }
                is Status.Success -> {
                    binding.progressCircular.isVisible = false
                }
            }
        }

        profileViewModel.setImageStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                is Status.Error -> {
                    Log.d("ERROR", status.message.toString())
                }
                is Status.Loading -> {
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_save_image),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Status.Success -> {
                    createSnackBar(
                        binding.root,
                        getString(R.string.msg_success_change_profile),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.btnAllFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_nav_profile_to_nav_favorite)
        }
    }

    private fun launchImagePicker() {
        ImagePicker.with(this)
            .compress(1000)
            .galleryMimeTypes(
                arrayOf(
                    "image/jpg",
                    "image/png",
                    "image/jpeg"
                )
            )
            .galleryOnly()
            .createIntent { intent ->
                startForProfileImageResult.launch(intent)
            }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    val fileUri = data?.data ?: return@registerForActivityResult
                    profileViewModel.uploadProfilePicture(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }
}