package com.rmaproject.sholattrackeranak.ui.home.jadwal_sholat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.sholattrackeranak.R
import com.rmaproject.sholattrackeranak.api.`interface`.JadwalSholatInterface
import com.rmaproject.sholattrackeranak.databinding.FragmentJadwalSholatBinding
import com.rmaproject.sholattrackeranak.viewmodel.JadwalSholatViewModel

class JadwalSholatFragment : Fragment(R.layout.fragment_jadwal_sholat) {

    private val binding: FragmentJadwalSholatBinding by viewBinding()
    private val viewModel: JadwalSholatViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jamSholatShubuh.text = viewModel.getJadwalSholat()?.results?.datetime?.get(0)?.times?.fajr ?: "--/--"
        binding.jamSholatDzuhur.text = viewModel.getJadwalSholat()?.results?.datetime?.get(0)?.times?.dhuhr ?: "--/--"
        binding.jamSholatAshar.text = viewModel.getJadwalSholat()?.results?.datetime?.get(0)?.times?.asr ?: "--/--"
        binding.jamSholatMaghrib.text = viewModel.getJadwalSholat()?.results?.datetime?.get(0)?.times?.maghrib ?: "--/--"
        binding.jamSholatIsya.text = viewModel.getJadwalSholat()?.results?.datetime?.get(0)?.times?.isha ?: "--/--"
    }
}