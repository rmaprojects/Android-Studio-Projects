package com.rmaprojects.githubapp.view.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rmaprojects.githubapp.view.detail.fragments.repos.RepositoryFragment
import com.rmaprojects.githubapp.view.detail.fragments.starred.StarredFragment

class ViewPagerAdapter(context:FragmentActivity, private val listFragment:List<Fragment>) : FragmentStateAdapter(context) {
    override fun getItemCount() = listFragment.size

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}