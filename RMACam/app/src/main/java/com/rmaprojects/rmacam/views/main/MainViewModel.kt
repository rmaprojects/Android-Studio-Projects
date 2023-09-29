package com.rmaprojects.rmacam.views.main

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.rmaprojects.rmacam.util.UploadStatus
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.Locale

class MainViewModel : ViewModel() {

    private val _photoFile = MutableLiveData<Uri>()

    private val _uploadStatus = MutableLiveData<UploadStatus>()

    fun setPhotoFile(uri: Uri) {
        _photoFile.value = uri
        uploadPhoto()
    }

    private fun uploadPhoto() {
        viewModelScope.launch {
            try {
                val fileName = "RMA_CAM_${
                    SimpleDateFormat("yyyy-mm-dd|HH:MM:ss", Locale.getDefault()).format(
                        System.currentTimeMillis()
                    )
                }"
                _photoFile.value?.let {
                    Firebase.storage.getReference("photo")
                        .child("$fileName.png")
                        .putFile(it).await()
                }
                _uploadStatus.value = UploadStatus.Success()
            } catch (e: Exception) {
                _uploadStatus.value = UploadStatus.Error(e.message ?: "Error occurred")
            }
        }
    }
}