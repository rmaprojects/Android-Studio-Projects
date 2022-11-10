package com.rmaprojects.submission1.pages.storylist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.data.preferences.UserInfo
import com.rmaprojects.submission1.databinding.FragmentListStoryBinding
import com.rmaprojects.submission1.showSnackbar

class ListStoryFragment : Fragment(R.layout.fragment_list_story) {

    private val viewModel : StoryViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private val binding : FragmentListStoryBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.stories.observe(viewLifecycleOwner) { stories ->
            stories.listStory?.let {
                val adapter = StoriesAdapter(it)
                binding.storyRv.adapter = adapter
                binding.storyRv.layoutManager = LinearLayoutManager(requireContext())
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingListener(isLoading)
        }
    }

    private fun loadingListener(isLoading: Boolean?) {
        if (isLoading == null) {
            binding.progressCircular.isVisible = false
            binding.storyRv.isVisible = false
            showSnackbar(binding.root, getString(R.string.alert_story_failed), Snackbar.LENGTH_SHORT)
            return
        }

        if (isLoading) {
            binding.storyRv.isVisible = false
            binding.progressCircular.isVisible = true
        } else {
            binding.storyRv.isVisible = true
            binding.progressCircular.isVisible = false
        }
    }

    override fun onStart() {
        super.onStart()

        if (UserInfo.token.isEmpty()) {
            findNavController().navigate(R.id.action_nav_list_story_to_loginFragment)
        }
    }
}