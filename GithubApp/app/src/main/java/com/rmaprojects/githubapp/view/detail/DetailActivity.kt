package com.rmaprojects.githubapp.view.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.rmaprojects.githubapp.R
import com.rmaprojects.githubapp.databinding.ActivityDetailBinding
import com.rmaprojects.githubapp.di.Injection
import com.rmaprojects.githubapp.view.detail.adapter.ViewPagerAdapter
import com.rmaprojects.githubapp.view.detail.fragments.repos.RepositoryFragment
import com.rmaprojects.githubapp.view.detail.fragments.starred.StarredFragment
import com.rmaprojects.githubapp.viewmodel.GithubViewModel
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private val binding:ActivityDetailBinding by viewBinding()
    private val viewModel:GithubViewModel by viewModels {
        Injection.provideGithubViewModelFactory(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val username = intent.getStringExtra(GET_USER)?: ""

        setViewPagerAdapter(username)
        applyTexts(username)
    }

    private fun setViewPagerAdapter(username: String) {
        val bundle = Bundle()
        bundle.putString(RepositoryFragment.GET_LOGIN, username)
        val fragmentList = listOf(
            RepositoryFragment().apply {
                arguments = bundle
            },
            StarredFragment()
        )
        val adapter = ViewPagerAdapter(this, fragmentList)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Repositories"
                1 -> tab.text = "Starred"
            }
        }
    }

    private fun applyTexts(username: String) {
        lifecycleScope.launch {
            val response = viewModel.getUserDetail(username)
            with(binding) {
                txtUsername.text = response.login?: "Unknown"
                txtFullName.text = response.name?: "Unknown"
                txtCompanion.text = response.company?: "Unknown"
                txtLocation.text = response.location?: "Unknown"
                txtTwitter.text = response.twitterUsername?: "Unknown"
                txtFollowers.text = "${response.followers?: "Unknown"} Followers | ${response.following?: "Unknown"} Following"
                Glide.with(this@DetailActivity)
                    .load(response.avatarUrl)
                    .into(imgAvatar)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val GET_USER = "GETUSER"
    }
}