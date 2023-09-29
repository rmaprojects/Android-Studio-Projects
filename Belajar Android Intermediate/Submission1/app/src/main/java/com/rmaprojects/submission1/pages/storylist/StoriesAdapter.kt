package com.rmaprojects.submission1.pages.storylist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.rmaprojects.submission1.R
import com.rmaprojects.submission1.data.api.model.stories.Story
import com.rmaprojects.submission1.databinding.ItemStoryBinding
import com.rmaprojects.submission1.pages.storylist.StoriesAdapter.StoriesViewHolder

class StoriesAdapter(
    private val listStory: List<Story>
) : RecyclerView.Adapter<StoriesViewHolder>() {

    var onClickListener: ((Story) -> Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoriesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false))

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        val story = listStory[position]
        holder.bindView(story)
        holder.binding.root.setOnClickListener {
            onClickListener?.invoke(story)
        }
    }

    class StoriesViewHolder(view:View) : RecyclerView.ViewHolder(view) {

        val binding : ItemStoryBinding by viewBinding()

        fun bindView(story: Story?) = with(binding) {
            story?.let {
                tvDetailName.text = it.name
                tvDetailDescription.text = it.description
                ivItemPhoto.load(it.photoUrl)
                txtDate.text = it.createdAt
            }
        }
    }

    override fun getItemCount(): Int = listStory.size
}