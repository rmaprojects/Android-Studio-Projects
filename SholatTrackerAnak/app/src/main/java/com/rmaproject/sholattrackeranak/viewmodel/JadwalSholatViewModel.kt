package com.rmaproject.sholattrackeranak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rmaproject.sholattrackeranak.api.model.JadwalSholatModel

class JadwalSholatViewModel: ViewModel() {


    private val jadwalSholat:MutableLiveData<JadwalSholatModel> = MutableLiveData<JadwalSholatModel>()

    fun getJadwalSholat(): JadwalSholatModel? {
        return jadwalSholat.value
    }

    fun setJadwalSholat(jadwalSholat: JadwalSholatModel) {
        this.jadwalSholat.value = jadwalSholat
    }
}