package com.rmaprojects.githubapp.view.detail.fragments.repos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.githubapp.R
import com.rmaprojects.githubapp.databinding.ItemReposBinding
import com.rmaprojects.githubapp.model.repouser.UserReposResponse
import com.rmaprojects.githubapp.model.repouser.UserReposResponseItem
import com.rmaprojects.githubapp.view.detail.fragments.repos.adapter.RepositoriesAdapter.ReposAdapterViewHolder

class RepositoriesAdapter(private val listRepo:UserReposResponse) : RecyclerView.Adapter<ReposAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposAdapterViewHolder = ReposAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_repos, parent, false))

    override fun onBindViewHolder(holder: ReposAdapterViewHolder, position: Int) {
        val repoItem = listRepo[position]
        holder.bindView(repoItem)
    }

    override fun getItemCount() = listRepo.size

    class ReposAdapterViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val binding:ItemReposBinding by viewBinding()

        fun bindView(repoItem: UserReposResponseItem) {
            with(binding) {
                txtRepoName.text = repoItem.name
                txtStargazer.text = repoItem.stargazersCount.toString()
                txtLanguage.text = repoItem.language
            }
        }

    }
}