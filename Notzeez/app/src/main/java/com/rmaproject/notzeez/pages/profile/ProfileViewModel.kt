package com.rmaproject.notzeez.pages.profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.UploadTask
import com.rmaproject.notzeez.repository.MainRepository
import com.rmaproject.notzeez.util.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val repository: MainRepository
): ViewModel() {

    private val _uploadImageStatus = MutableLiveData<Status<UploadTask.TaskSnapshot>>()
    val uploadImageStatus: LiveData<Status<UploadTask.TaskSnapshot>> = _uploadImageStatus

    private val _setImageStatus = MutableLiveData<Status<Void>>()
    val setImageStatus:LiveData<Status<Void>> = _setImageStatus

    fun uploadProfilePicture(imageUri: Uri) {
        _uploadImageStatus.postValue(Status.Loading())
        viewModelScope.launch(Dispatchers.Main) {
            val status = repository.uploadProfilePicture(imageUri)
            status.data?.metadata?.reference?.downloadUrl?.addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    _uploadImageStatus.postValue(Status.Error(task.exception?.message?: "Error"))
                }
                setProfilePicture(task.result.toString())
            }
            _uploadImageStatus.postValue(status)
        }
    }

    private fun setProfilePicture(imageUrl: String) {
        _setImageStatus.postValue(Status.Loading())
        viewModelScope.launch {
            val status = repository.changeProfilePicture(imageUrl)
            _setImageStatus.postValue(status)
        }
    }

    fun deleteAllNotes() = viewModelScope.launch{ repository.deleteAllNotes() }

}
