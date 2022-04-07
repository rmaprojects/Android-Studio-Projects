package com.rmaproject.sholattrackeranak.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rmaproject.sholattrackeranak.ui.home.history.HistoryFragment
import com.rmaproject.sholattrackeranak.ui.home.jadwal_sholat.JadwalSholatFragment
import com.rmaproject.sholattrackeranak.ui.home.setor_sholat.SetorSholatFragment

class ViewPageAdapter(context:FragmentActivity) : FragmentStateAdapter(context) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                SetorSholatFragment()
            }
            1 -> {
                HistoryFragment()
            }
            2 -> {
                JadwalSholatFragment()
            }
            else -> throw Exception("Position not found")
        }
    }
}