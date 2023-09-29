package com.rmaprojects.submission1.pages.storydetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.submission1.data.api.model.detail.Story
import com.rmaprojects.submission1.data.repository.StoriesRepository
import com.rmaprojects.submission1.utils.getToken
import kotlinx.coroutines.launch

class StoryDetailViewModel(private val repository: StoriesRepository) : ViewModel() {

    private val _storyDetail = MutableLiveData<Story?>()
    val storyDetail : LiveData<Story?> = _storyDetail

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading : LiveData<Boolean?> = _isLoading

    fun getStoryDetail(storyId : String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = repository.getDetailStory(storyId)
                if (!response.error) {
                    _storyDetail.postValue(response.story)
                    _isLoading.postValue(false)
                    return@launch
                }
                _isLoading.postValue(null)
                _storyDetail.postValue(null)
            } catch (e:Exception) {
                Log.d("ERROR", e.toString())
                _isLoading.postValue(null)
            }
        }
    }
}