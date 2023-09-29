package com.dicoding.newsapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.newsapp.data.NewsRepository
import com.dicoding.newsapp.utils.DataDummy
import com.dicoding.newsapp.utils.MainDispatcherRule
import com.dicoding.newsapp.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsRepository: NewsRepository
    private lateinit var newsViewModel: NewsDetailViewModel
    private val dummyDetailNews = DataDummy.generateDummyNewsEntity()[0]

    @Before
    fun setUp() {
        newsViewModel = NewsDetailViewModel(newsRepository)
        newsViewModel.setNewsData(dummyDetailNews)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `when bookmarkStatus false should call saveNews`() = runTest {
        val expectedBoolean = MutableLiveData<Boolean>()
        expectedBoolean.value = false
        `when`(newsRepository.isNewsBookmarked(dummyDetailNews.title)).thenReturn(expectedBoolean)
        newsViewModel.bookmarkStatus.getOrAwaitValue()
        newsViewModel.changeBookmark(dummyDetailNews)
        Mockito.verify(newsRepository).saveNews(dummyDetailNews)
    }

    @Test
    fun `when bookmarksStatus true should call deleteNews`() = runTest {
        val expectedBoolean = MutableLiveData<Boolean>()
        expectedBoolean.value = true
        `when`(newsRepository.isNewsBookmarked(dummyDetailNews.title)).thenReturn(expectedBoolean)
        newsViewModel.bookmarkStatus.getOrAwaitValue()
        newsViewModel.changeBookmark(dummyDetailNews)
        Mockito.verify(newsRepository).deleteNews(dummyDetailNews.title)
    }
}

// Untuk menjalankan coroutines di test setup dispatcher nya dulu.