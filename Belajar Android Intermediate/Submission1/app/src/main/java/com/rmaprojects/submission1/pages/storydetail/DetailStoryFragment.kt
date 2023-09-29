package com.rmaprojects.submission1.pages.storydetail

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.databinding.FragmentDetailStoryBinding
import com.rmaprojects.submission1.utils.showSnackbar

class DetailStoryFragment : Fragment(R.layout.fragment_detail_story) {

    private val binding: FragmentDetailStoryBinding by viewBinding()
    private val viewModel: StoryDetailViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.explode)
        enterTransition = inflater.inflateTransition(android.R.transition.explode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val storyId = arguments?.getString(STORY_ID_KEY) ?: ""
        viewModel.getStoryDetail(storyId)

        viewModel.storyDetail.observe(viewLifecycleOwner) { story ->
            if (story == null) {
                showSnackbar(
                    binding.root,
                    getString(R.string.fail_load_detail),
                    Snackbar.LENGTH_SHORT
                )
            } else
                with(binding) {
                    ivItemPhoto.load(story.photoUrl)
                    tvDetailName.text = story.name
                    tvDetailDescription.text = story.description
                    txtDate.text = story.createdAt
                }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingListener(isLoading)
        }

    }

    private fun loadingListener(loading: Boolean?) {
        if (loading == false) {
            binding.loadedContent.isVisible = true
            binding.progressCircular.isVisible = false
        }

        if (loading == true) {
            binding.loadedContent.isVisible = false
            binding.progressCircular.isVisible = true
        }

        if (loading == null) {
            binding.loadedContent.isVisible = false
            binding.progressCircular.isVisible = false
            showSnackbar(binding.root, getString(R.string.fail_load_detail), Snackbar.LENGTH_SHORT)
        }
    }

    companion object {
        const val STORY_ID_KEY = "STORY_ID"
    }
}