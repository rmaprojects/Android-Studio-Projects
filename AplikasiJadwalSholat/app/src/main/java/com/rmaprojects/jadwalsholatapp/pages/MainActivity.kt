package com.rmaprojects.jadwalsholatapp.pages

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import androidx.core.location.LocationManagerCompat
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rmaprojects.jadwalsholatapp.R
import com.rmaprojects.jadwalsholatapp.data.ViewModelFactory
import com.rmaprojects.jadwalsholatapp.data.api.model.prayertime.Time
import com.rmaprojects.jadwalsholatapp.databinding.ActivityMainBinding
import mumayank.com.airlocationlibrary.AirLocation
import java.util.*

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModels {
        ViewModelFactory.getInstance()
    }

    private lateinit var airLocation: AirLocation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isLocationEnabled(this)) {
            airLocation = AirLocation(this, object : AirLocation.Callback {
                override fun onFailure(locationFailedEnum: AirLocation.LocationFailedEnum) {
                    Log.e("LOCATION ERROR", locationFailedEnum.toString())
                }

                override fun onSuccess(locations: ArrayList<Location>) {
                    val longitude = locations[0].longitude
                    val latitude = locations[0].latitude
                    viewModel.getPrayerTime(longitude, latitude)
                }

            }, true)
            airLocation.start()
        } else {
            Toast.makeText(
                this@MainActivity,
                "Lokasi anda gagal didapatkan, beralih ke lokasi default",
                Toast.LENGTH_SHORT
            ).show()
            viewModel.getPrayerTime(107.1117184, -6.386038)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            setLoading(isLoading)
        }

        viewModel.currentPrayerTime.observe(this) { prayerTimeModel ->
            val prayerTimes = prayerTimeModel?.times?.get(0)
            setPrayerTime(prayerTimes)
        }
    }

    private fun setPrayerTime(prayerTimes: Time?) = with(binding) {
        prayerTimes?.let { prayerTime ->
            txtClockShubuh.text = prayerTime.fajr
            txtClockDzuhur.text = prayerTime.dhuhr
            txtClockAshar.text = prayerTime.asr
            txtClockMaghrib.text = prayerTime.maghrib
            txtClockIsya.text = prayerTime.isha
        }
    }

    private fun setLoading(isLoading: Boolean?) {
        if (isLoading != null) {
            binding.layoutPrayerTimes.isVisible = !isLoading
            binding.progressIndicator.isVisible = isLoading
            if (!isLoading) {
                setCurrentLocation()
            }
        } else {
            binding.layoutPrayerTimes.isVisible = false
            binding.progressIndicator.isVisible = false
            binding.txtYourInternetSucks.isVisible = true
        }
    }

    private fun setCurrentLocation() {
        viewModel.myAddress.observe(this) { address ->
            if (VERSION.SDK_INT == Build.VERSION_CODES.TIRAMISU) {
                Geocoder(this@MainActivity, Locale.getDefault()).getFromLocation(
                        address.latitude,
                        address.longitude,
                        1
                    ) { geoCoder ->
                        setLocationText(geoCoder)
                    }
            } else {
                @Suppress("DEPRECATION") val geoCoder = Geocoder(
                    this@MainActivity,
                    Locale.getDefault()
                ).getFromLocation(address.latitude, address.longitude, 1)
                geoCoder?.let { setLocationText(it) }
            }
        }
    }

    private fun setLocationText(geoCoder: List<Address>) {
        val locality = geoCoder[0].locality
        val subLocality = geoCoder[0].subLocality
        val subAdminArea = geoCoder[0].subAdminArea
        val myLocationNow = "Lokasi anda:\n$subLocality, $locality, $subAdminArea"
        binding.txtMyLocation.text = myLocationNow
    }

    private fun isLocationEnabled(context: Context): Boolean {
        val locationManager = context.getSystemService<LocationManager>()!!
        return LocationManagerCompat.isLocationEnabled(locationManager)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}