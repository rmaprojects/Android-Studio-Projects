package com.rmaproject.sholattrackeranak.ui.home.setor_sholat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaproject.sholattrackeranak.MainActivity
import com.rmaproject.sholattrackeranak.R
import com.rmaproject.sholattrackeranak.api.model.NamaAnakModel
import com.rmaproject.sholattrackeranak.databinding.FragmentSetorSholatBinding
import com.rmaproject.sholattrackeranak.settings.TinyDB
import com.rmaproject.sholattrackeranak.ui.adapter.NamaAnakAdapter

class SetorSholatFragment : Fragment(R.layout.fragment_setor_sholat) {

    private val binding: FragmentSetorSholatBinding by viewBinding()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tinyDb = TinyDB(requireContext())
        val listNamaAnak = tinyDb.getObject(MainActivity.DAFTARANAKKEY, NamaAnakModel::class.java)
        val adapter = NamaAnakAdapter(listNamaAnak)

        binding.recyclerView.adapter = adapter

        adapter.listener = {
            findNavController().navigate(R.id.action_nav_setor_sholat_to_kirimSholatDialogFragment)
        }
    }
}