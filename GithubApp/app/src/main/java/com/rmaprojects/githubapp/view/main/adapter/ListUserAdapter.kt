package com.rmaprojects.githubapp.view.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.rmaprojects.githubapp.R
import com.rmaprojects.githubapp.databinding.ItemListUserBinding
import com.rmaprojects.githubapp.model.userlist.UserListResponse
import com.rmaprojects.githubapp.model.userlist.UserListResponseItem
import com.rmaprojects.githubapp.view.main.adapter.ListUserAdapter.ListUserAdapterViewHolder

class ListUserAdapter(private val userList: UserListResponse) : RecyclerView.Adapter<ListUserAdapterViewHolder>() {

    var itemOnClick:((UserListResponseItem) -> Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListUserAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false))

    override fun onBindViewHolder(holder: ListUserAdapterViewHolder, position: Int) {
        val userItem = userList[position]
        holder.bindView(userItem)
        holder.binding.cardClickable.setOnClickListener {
            itemOnClick?.invoke(userItem)
        }
    }

    override fun getItemCount() = userList.size

    class ListUserAdapterViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        val binding:ItemListUserBinding by viewBinding()
        fun bindView(userItem: UserListResponseItem) {
            with (binding) {
                txtUsername.text = userItem.login
                txtGithubUrl.text = "www.github.com/${userItem.login}"
                Glide.with(itemView.context)
                    .load(userItem.avatarUrl)
                    .into(imgAvatar)
            }
        }

    }
}