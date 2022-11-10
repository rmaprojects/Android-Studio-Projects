package com.rmaprojects.githubapp.data

import com.rmaprojects.githubapp.model.detailuser.UserDetailResponse
import com.rmaprojects.githubapp.model.repouser.UserReposResponse
import com.rmaprojects.githubapp.model.searchrepo.SearchReposResponse
import com.rmaprojects.githubapp.model.searchuser.SearchUserResponse
import com.rmaprojects.githubapp.model.userlist.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("users")
    suspend fun getListUser(
    ) : UserListResponse

    @GET("users/{username}")
    suspend fun getUserDetail(
        @Path("username") username:String
    ) : UserDetailResponse

    @GET("search/users")
    suspend fun searchUser(
        @Query("q") username:String
    ) : SearchUserResponse

    @GET("search/repositories")
    suspend fun searchRepo(
        @Query("q") repoName:String
    ) : SearchReposResponse

    @GET("users/{username}/repos")
    suspend fun getUserRepos(
        @Path("username") username:String
    ) : UserReposResponse


    companion object {
        private const val BASE_URL = "https://api.github.com/"
        fun create() : GithubApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GithubApi::class.java)
        }
    }
}