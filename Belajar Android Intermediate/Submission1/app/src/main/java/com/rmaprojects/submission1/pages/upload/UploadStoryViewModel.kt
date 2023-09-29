package com.rmaprojects.submission1.pages.upload

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rmaprojects.submission1.data.repository.StoriesRepository
import com.rmaprojects.submission1.utils.buildImageBodyPart
import com.rmaprojects.submission1.utils.convertToFile
import com.rmaprojects.submission1.utils.setRequestBody
import kotlinx.coroutines.launch

class UploadStoryViewModel(private val repository: StoriesRepository) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean?>()
    val isLoading: LiveData<Boolean?> = _isLoading

    private val _image = MutableLiveData<Bitmap?>()
    val image: LiveData<Bitmap?> = _image


    fun setImageFile(newImage: Bitmap) = _image.postValue(newImage)

    fun uploadStory(context: Context, description: String) {
        _isLoading.postValue(true)
        val file = _image.value?.convertToFile(context, description)
        viewModelScope.launch {
            try {
                val response = repository.uploadStories(
                    description.setRequestBody(),
                    file?.buildImageBodyPart(),
                )
                if (response.error) {
                    _isLoading.postValue(null)
                    return@launch
                }
                _isLoading.postValue(false)
            } catch (e: Exception) {
                Log.d("ERROR", e.toString())
                _isLoading.postValue(null)
            }
        }
    }
}