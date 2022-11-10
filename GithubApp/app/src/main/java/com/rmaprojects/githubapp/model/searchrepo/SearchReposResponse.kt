package com.rmaprojects.githubapp.model.searchrepo


import com.google.gson.annotations.SerializedName

data class SearchReposResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean?,
    @SerializedName("items")
    val items: List<Item?>?,
    @SerializedName("total_count")
    val totalCount: Int?
)