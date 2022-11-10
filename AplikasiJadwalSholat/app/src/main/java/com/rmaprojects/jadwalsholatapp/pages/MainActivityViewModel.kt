package com.rmaprojects.jadwalsholatapp.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.jadwalsholatapp.data.api.model.prayertime.PrayerTimeModel
import com.rmaprojects.jadwalsholatapp.data.repository.Repository
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _currentPrayerTime = MutableLiveData<PrayerTimeModel?>()
    val currentPrayerTime: LiveData<PrayerTimeModel?> = _currentPrayerTime

    private val _myAddress = MutableLiveData<MyAddress>()
    val myAddress: LiveData<MyAddress> = _myAddress

    fun getPrayerTime(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            _isLoading.postValue(true)
            try {
                val response = repository.getPrayerTime(longitude, latitude)
                _currentPrayerTime.postValue(response)
                _myAddress.postValue(MyAddress(longitude, latitude))
                _isLoading.postValue(false)
            } catch (e: Exception) {
                _isLoading.postValue(null)
            }
        }
    }
}

data class MyAddress(
    val longitude: Double,
    val latitude: Double
)