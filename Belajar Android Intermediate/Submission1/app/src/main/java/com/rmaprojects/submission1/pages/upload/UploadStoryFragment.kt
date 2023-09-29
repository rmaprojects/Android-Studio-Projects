package com.rmaprojects.submission1.pages.upload

import android.app.Activity
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.transition.TransitionInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.databinding.FragmentAddStoryBinding
import com.rmaprojects.submission1.utils.showSnackbar

class UploadStoryFragment : Fragment(R.layout.fragment_add_story) {

    private val binding : FragmentAddStoryBinding by viewBinding()
    private val viewModel : UploadStoryViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private var uriSelected : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.explode)
        enterTransition = inflater.inflateTransition(android.R.transition.explode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.image.observe(viewLifecycleOwner) { file ->
            binding.buttonAdd.setOnClickListener {

                val description = binding.inputDescription.editText?.text.toString()

                if (file == null) {
                    Toast.makeText(requireContext(), "Please select an image from your gallery", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                if (description.isEmpty()) {
                    binding.inputDescription.error =  "Please input this field"
                    return@setOnClickListener
                }

                viewModel.uploadStory(requireContext(), description)
            }
        }

        binding.containerNewStory.setOnClickListener {
            ImagePicker.with(this)
                .compress(1000)
                .setDismissListener {
                    Toast.makeText(requireContext(), "Dismissed", Toast.LENGTH_SHORT).show()
                }
                .galleryMimeTypes(arrayOf(
                    "image/*"
                ))
                .createIntent {
                    startForProfileImageResult.launch(it)
                }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingListener(isLoading)
        }
    }

    private fun loadingListener(loading: Boolean?) {
        if (loading == null) {
            showSnackbar(binding.root, getString(R.string.fail_upload_img), Snackbar.LENGTH_SHORT)
            return
        }

        if (loading == false) {
            binding.buttonAdd.isEnabled = true
            binding.progressCircular.isVisible = false
            findNavController().navigateUp()
            Toast.makeText(requireContext(), getString(R.string.success_img_upload), Toast.LENGTH_SHORT).show()
        }

        if (loading == true) {
            binding.buttonAdd.isEnabled = false
            binding.progressCircular.isVisible = true
        }
    }

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val data = result.data
            when (result.resultCode) {
                Activity.RESULT_OK -> {
                    uriSelected = data?.data!!
                    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        ImageDecoder.decodeBitmap(ImageDecoder.createSource(requireContext().contentResolver,
                            uriSelected!!
                        ))
                    } else {
                        @Suppress("DEPRECATION")
                        MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uriSelected)
                    }

                    requireView().findViewById<ImageView>(R.id.img_new_story).load(bitmap)
                    viewModel.setImageFile(bitmap)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        }
}