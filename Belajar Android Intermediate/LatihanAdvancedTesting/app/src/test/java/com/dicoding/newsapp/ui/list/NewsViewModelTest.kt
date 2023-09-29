package com.dicoding.newsapp.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.dicoding.newsapp.data.NewsRepository
import com.dicoding.newsapp.data.Result
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.utils.DataDummy
import com.dicoding.newsapp.utils.getOrAwaitValue
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

// Untuk kelas yang menggunakan annotation @Mock dari Mockito,
// kita wajib menggunakan annotation @RunWith(MockitoJUnitRunner::class)
@RunWith(MockitoJUnitRunner::class)
//MockitoJUnitRunner merupakan kelas yang digunakan untuk membuat dan memvalidasi proses mock.
class NewsViewModelTest {

    /**
     * Rule ini digunakan supaya background process dari Architecture Component yang dijalankan pada kelas ini dijalankan secara synchronous.
     * Mengapa ini penting? Hal ini karena jika proses dijalankan di background, kode untuk Assert akan langsung dijalankan sebelum hasilnya didapatkan,
     * sehingga proses pengecekan akan gagal.
     *
     * Dengan menjalankan secara synchronous, maka kode ketika mengambil data dari LiveData akan dieksekusi dulu sampai selesai
     * baru lanjut ke proses berikutnya, sehingga pengecekan akan sesuai dengan yang diharapkan.
     */
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

//  Untuk membuat mock dari suatu object, Anda cukup membuat lateinit varibel dengan anotasi @Mock
    @Mock
    private lateinit var newsRepository: NewsRepository
//  Secara otomatis nanti Mockito akan menginisialisasinya dengan object tiruan.


    private lateinit var newsViewModel: NewsViewModel
    private val dummyNews = DataDummy.generateDummyNewsEntity()

    @Before
    fun setUp() {
        newsViewModel = NewsViewModel(newsRepository)
    }

    @Test
    fun `when GET headline news should not null and return Success`() {
        val expectedNews = MutableLiveData<Result<List<NewsEntity>>>()
        expectedNews.value = Result.Success(dummyNews)
        `when`(newsRepository.getHeadlineNews()).thenReturn(expectedNews)

        val actualNews = newsViewModel.getHeadlineNews().getOrAwaitValue()
        Mockito.verify(newsRepository).getHeadlineNews()
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Success)
        Assert.assertEquals(dummyNews.size, (actualNews as Result.Success).data.size)
    }

    @Test
    fun `when network error should return error`() {
        val headlineNews = MutableLiveData<Result<List<NewsEntity>>>()
        headlineNews.value = Result.Error("Error")
        `when`(newsRepository.getHeadlineNews()).thenReturn(headlineNews)
        /**
        Kita bisa membaca kode di atas seperti berikut.
        “  Ketika newsRepository.getHeadlineNews dipanggil, maka akan mengembalikan nilai dari headlineNews.”
        Jadi, ia tidak memanggil fungsi asli yang di dalamnya, tetapi hanya mengembalikan nilai seperti yang kita atur.
        Selain menggunakan when..thenReturn, Anda juga bisa menggunakan when..thenThrow dan doThrow.. then.
         */
        val actualNews = newsViewModel.getHeadlineNews().getOrAwaitValue()
        Mockito.verify(newsRepository).getHeadlineNews()
        Assert.assertNotNull(actualNews)
        Assert.assertTrue(actualNews is Result.Error)
    }

}

/**
Konsep dasar stub adalah menyimulasikan data yang didapat ketika memanggil suatu fungsi.
Untuk itu, kita siapkan data dummy-nya terlebih dahulu

Fungsi dari mock object adalah untuk mereplika obyek yang digunakan oleh obyek yang sedang Anda test.
Tujuannya agar test yang dilakukan hanya dilakukan pada unit yang berada di dalam jangkauan obyek
yang sedang di-test tanpa memengaruhi obyek yang ada di luar jangkauan.



 * */