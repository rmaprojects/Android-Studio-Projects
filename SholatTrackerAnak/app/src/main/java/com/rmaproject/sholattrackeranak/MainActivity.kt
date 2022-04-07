package com.rmaproject.sholattrackeranak

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.rmaproject.sholattrackeranak.api.`interface`.ApiInterface
import com.rmaproject.sholattrackeranak.api.`interface`.JadwalSholatInterface
import com.rmaproject.sholattrackeranak.api.model.GetNamaAnakModel
import com.rmaproject.sholattrackeranak.api.model.GetNamaAnakModelItem
import com.rmaproject.sholattrackeranak.api.model.NamaAnakModel
import com.rmaproject.sholattrackeranak.databinding.ActivityMainBinding
import com.rmaproject.sholattrackeranak.settings.TinyDB
import com.rmaproject.sholattrackeranak.ui.adapter.ViewPageAdapter
import com.rmaproject.sholattrackeranak.viewmodel.JadwalSholatViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: JadwalSholatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        binding.viewPager.adapter = ViewPageAdapter(this)
        val tinyDb = TinyDB(this)
        retrieveKidsNames(tinyDb)
        retrieveJadwalSholat()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Setor Sholat"
                1 -> "Riwayat"
                2 -> "Jadwal Sholat"
                else -> "Unknown"
            }
            tab.icon = when (position) {
                0 -> AppCompatResources.getDrawable(this, R.drawable.ic_baseline_verified_24)
                1 -> AppCompatResources.getDrawable(this, R.drawable.ic_baseline_history_24)
                2 -> AppCompatResources.getDrawable(this, R.drawable.ic_baseline_schedule_24)
                else -> throw Exception("Unknown position")
            }
        }.attach()
    }

    private fun retrieveJadwalSholat() {
        val api = JadwalSholatInterface.createApi()
        lifecycleScope.launch {
            try {
                val response = api.getJadwalSholat("Bekasi")
                if (response.isSuccessful) {
                    val jadwalSholat = response.body()
                    viewModel.setJadwalSholat(jadwalSholat!!)
                    Log.d("JadwalSholat", jadwalSholat.toString())
                } else {
                    Log.d("retrieveJadwalSholat", "Error: ${response.errorBody()}")
                }
            } catch (e:Exception) {
                Log.d("retrieveJadwalSholat", e.message.toString())
                Snackbar.make(binding.root, "Gagal mengambil data, periksa kembali internet anda!", Snackbar.LENGTH_LONG)
                    .setAction("Ok"){}
                    .show()
            }
        }
    }

    private fun retrieveKidsNames(tinyDB: TinyDB) {
        val api = ApiInterface.createApi()
        lifecycleScope.launch {
            try {
                val response = api.getDaftarAnak("*")
                if (response.isSuccessful) {
                    tinyDB.putObject(DAFTARANAKKEY, NamaAnakModel(response.body()!!))
                } else {
                    Log.d("response", response.body().toString())
                }
            } catch (e: Exception) {
                Log.d("Error", e.toString())
                Snackbar.make(binding.root, "Gagal mengambil data anak, aktifkan internet anda", Snackbar.LENGTH_LONG)
                    .setAction("Ok"){}
                    .show()
            }
        }
    }

    companion object {
        const val DAFTARANAKKEY = "DAFTARANAK"
    }
}