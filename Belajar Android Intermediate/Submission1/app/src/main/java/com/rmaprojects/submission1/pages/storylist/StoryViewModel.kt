package com.rmaprojects.submission1.pages.storylist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.submission1.data.api.model.stories.StoriesResponse
import com.rmaprojects.submission1.data.preferences.UserInfo
import com.rmaprojects.submission1.data.repository.StoriesRepository
import com.rmaprojects.submission1.getToken
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoriesRepository) : ViewModel() {

    private val _stories = MutableLiveData<StoriesResponse>()
    val stories : LiveData<StoriesResponse> = _stories

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading : LiveData<Boolean?> = _isLoading

    init {
        getStories()
    }

    private fun getStories() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.getStories(getToken())
                _stories.postValue(response)
                _isLoading.postValue(false)
            } catch (e:Exception) {
                _isLoading.postValue(null)
                Log.d("Error", e.toString())
            }
        }
    }
}