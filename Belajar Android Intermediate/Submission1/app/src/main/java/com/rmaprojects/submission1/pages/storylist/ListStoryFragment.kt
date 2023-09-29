package com.rmaprojects.submission1.pages.storylist

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.ViewModelFactory
import com.rmaprojects.submission1.data.preferences.UserInfo
import com.rmaprojects.submission1.databinding.FragmentListStoryBinding
import com.rmaprojects.submission1.pages.storydetail.DetailStoryFragment
import com.rmaprojects.submission1.utils.showSnackbar

class ListStoryFragment : Fragment(R.layout.fragment_list_story) {

    private val viewModel : StoryViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private val binding: FragmentListStoryBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(android.R.transition.explode)
        reenterTransition = inflater.inflateTransition(android.R.transition.explode)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost : MenuHost = requireActivity()

        createContextMenu(menuHost)

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            loadingListener(isLoading)
        }

        viewModel.stories.observe(viewLifecycleOwner) { storyResponse ->
            val storyList = storyResponse.listStory
            if (storyList.isNullOrEmpty()) {
                return@observe
            }
            binding.storyRv.adapter = StoriesAdapter(storyList).apply {
                onClickListener = { story ->
                    val bundle = bundleOf(
                        DetailStoryFragment.STORY_ID_KEY to story.id
                    )
                    findNavController().navigate(R.id.action_nav_list_story_to_detailStoryFragment, bundle)
                }
            }
            binding.storyRv.layoutManager = LinearLayoutManager(requireContext())


        }
    }

    private fun loadingListener(isLoading: Boolean?) = with(binding) {

        if (isLoading == true) {
            progressCircular.isVisible = true
            storyRv.isVisible = false
        }

        if (isLoading == false){
            progressCircular.isVisible = false
            storyRv.isVisible = true
        }

        if (isLoading == null) {
            showSnackbar(root, getString(R.string.err_no_internet), Snackbar.LENGTH_SHORT)
            return@with
        }
    }

    override fun onStart() {
        super.onStart()
        if (UserInfo.token.isEmpty()) {
            findNavController().navigate(R.id.action_nav_list_story_to_loginFragment)
        }

        viewModel.getStories()
    }

    private fun createContextMenu(menuHost: MenuHost) {
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.main_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_logout -> {
                        MaterialAlertDialogBuilder(requireContext())
                            .setIcon(R.drawable.ic_baseline_logout_24)
                            .setTitle("Log out")
                            .setMessage(getString(R.string.warn_logout))
                            .setNegativeButton(getString(R.string.txt_cancel)) { _, _ -> }
                            .setPositiveButton("Ya") { _, _ ->
                                UserInfo.clear()
                                findNavController().navigate(R.id.action_nav_list_story_to_loginFragment)
                            }
                            .create().show()
                        true
                    }
                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}