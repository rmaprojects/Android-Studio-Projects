package com.rmaprojects.submission1.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rmaprojects.submission1.data.repository.StoriesRepository
import com.rmaprojects.submission1.di.Injection
import com.rmaprojects.submission1.pages.auth.AuthViewModel
import com.rmaprojects.submission1.pages.storydetail.StoryDetailViewModel
import com.rmaprojects.submission1.pages.storylist.StoryViewModel
import com.rmaprojects.submission1.pages.upload.UploadStoryViewModel


class ViewModelFactory(
    private val repository: StoriesRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> return AuthViewModel(repository) as T
            modelClass.isAssignableFrom(StoryViewModel::class.java) -> return StoryViewModel(repository) as T
            modelClass.isAssignableFrom(StoryDetailViewModel::class.java) -> return StoryDetailViewModel(repository) as T
            modelClass.isAssignableFrom(UploadStoryViewModel::class.java) -> return UploadStoryViewModel(repository) as T
        }

        throw Exception("Unknown ViewModel: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE:ViewModelFactory?= null
        fun getInstance() : ViewModelFactory = INSTANCE?: synchronized(this) {
            INSTANCE?: ViewModelFactory(Injection.provideRepository())
        }.also { INSTANCE = it }
    }
}