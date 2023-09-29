package com.rmaprojects.rmacam.views.video

import android.net.Uri
import androidx.lifecycle.LiveData
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

class VideoViewModel : ViewModel() {
    private val _videoFile = MutableLiveData<Uri>()

    private val _uploadStatus = MutableLiveData<UploadStatus>()
    val uploadStatus: LiveData<UploadStatus> = _uploadStatus

    fun setVideoFile(uri: Uri) {
        _videoFile.value = uri
        uploadVideo()
    }

    private fun uploadVideo() {
        viewModelScope.launch {
            try {
                val fileName = "RMA_CAM_${
                    SimpleDateFormat("yyyy-mm-dd|HH:MM:ss", Locale.getDefault()).format(
                        System.currentTimeMillis()
                    )
                }"
                _videoFile.value?.let {
                    Firebase.storage.getReference("video")
                        .child("$fileName.mp4")
                        .putFile(it).await()
                }
                _uploadStatus.value = UploadStatus.Success(_videoFile.value)
            } catch (e: Exception) {
                _uploadStatus.value = UploadStatus.Error(e.message ?: "Error occurred")
            }
        }
    }
}