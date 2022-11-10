package com.rmaprojects.githubapp.viewmodel

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.rmaprojects.githubapp.model.detailuser.UserDetailResponse
import com.rmaprojects.githubapp.model.repouser.UserReposResponse
import com.rmaprojects.githubapp.model.searchrepo.SearchReposResponse
import com.rmaprojects.githubapp.model.searchuser.SearchUserResponse
import com.rmaprojects.githubapp.model.userlist.UserListResponse
import com.rmaprojects.githubapp.repository.GithubRepository
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.IllegalArgumentException

class GithubViewModel(private val githubRepo:GithubRepository) : ViewModel() {

    suspend fun getListUser(
    ) : UserListResponse {
        return githubRepo.getListUser()
    }

    suspend fun getUserDetail(
        username:String
    ) : UserDetailResponse {
        return githubRepo.getUserDetail(username)
    }

    suspend fun searchUser(
        username:String
    ) : SearchUserResponse {
        return githubRepo.searchUser(username)
    }

    suspend fun searchRepo(
        repoName:String
    ) : SearchReposResponse {
        return githubRepo.searchRepo(repoName)
    }

    suspend fun getUserRepos(
        username:String
    ) : UserReposResponse {
        return githubRepo.getUserRepos(username)
    }
}

class ViewModelFactory(
    owner:SavedStateRegistryOwner,
    private val repository: GithubRepository
) : AbstractSavedStateViewModelFactory(owner, null){
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}