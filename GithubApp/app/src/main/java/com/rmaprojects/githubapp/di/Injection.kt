package com.rmaprojects.githubapp.di

import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.rmaprojects.githubapp.data.GithubApi
import com.rmaprojects.githubapp.repository.GithubRepository
import com.rmaprojects.githubapp.viewmodel.ViewModelFactory

object Injection {

    fun provideGithubApi() = GithubApi.create()

    fun provideGithubRepository() = GithubRepository(provideGithubApi())

    fun provideGithubViewModelFactory(owner: SavedStateRegistryOwner) : ViewModelProvider.Factory {
        return ViewModelFactory(owner, provideGithubRepository())
    }
}