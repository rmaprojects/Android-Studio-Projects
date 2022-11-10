package com.rmaprojects.githubapp.model.searchuser


import com.google.gson.annotations.SerializedName
import com.rmaprojects.githubapp.model.userlist.UserListResponse
import com.rmaprojects.githubapp.model.userlist.UserListResponseItem

data class SearchUserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: UserListResponse?,
    @SerializedName("total_count")
    val totalCount: Int?
)