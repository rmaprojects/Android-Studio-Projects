package com.rmaproject.radiorodjaplayer.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rmaproject.radiorodjaplayer.ui.home.radio.RadioFragment
import com.rmaproject.radiorodjaplayer.ui.home.tv.TvFragment

class ViewPagerAdapter(context : FragmentActivity) : FragmentStateAdapter(context) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RadioFragment()
            1 -> TvFragment()
            else -> throw IndexOutOfBoundsException("Invalid position")
        }
    }
}