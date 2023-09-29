package com.rmaprojects.submission1.pages.storylist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.submission1.data.api.model.stories.StoriesResponse
import com.rmaprojects.submission1.data.repository.StoriesRepository
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoriesRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _stories = MutableLiveData<StoriesResponse>()
    val stories: LiveData<StoriesResponse> = _stories

    fun getStories() {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.getStories()
                _stories.postValue(response)
                _isLoading.postValue(false)
            } catch (e: Exception) {
                Log.d("ERROR",e.toString())
                _isLoading.postValue(null)
            }
        }
    }
}