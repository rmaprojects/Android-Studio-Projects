package com.rmaprojects.githubapp.repository

import com.rmaprojects.githubapp.data.GithubApi
import com.rmaprojects.githubapp.model.detailuser.UserDetailResponse
import com.rmaprojects.githubapp.model.repouser.UserReposResponse
import com.rmaprojects.githubapp.model.searchrepo.SearchReposResponse
import com.rmaprojects.githubapp.model.searchuser.SearchUserResponse
import com.rmaprojects.githubapp.model.userlist.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class GithubRepository(val githubApi: GithubApi) {

    suspend fun getListUser(
    ) : UserListResponse {
        return githubApi.getListUser()
    }

    suspend fun getUserDetail(
        username:String
    ) : UserDetailResponse {
        return githubApi.getUserDetail(username)
    }

    suspend fun searchUser(
        username:String
    ) : SearchUserResponse {
        return githubApi.searchUser(username)
    }

    suspend fun searchRepo(
        repoName:String
    ) : SearchReposResponse {
        return githubApi.searchRepo(repoName)
    }

    suspend fun getUserRepos(
        username:String
    ) : UserReposResponse {
        return githubApi.getUserRepos(username)
    }


}