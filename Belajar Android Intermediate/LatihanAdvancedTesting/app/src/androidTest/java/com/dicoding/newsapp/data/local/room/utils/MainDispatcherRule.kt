package com.dicoding.newsapp.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * MainDispatcherRule extend ke TestWatcher yang mengimplementasikan interface TestRule.
 * Inilah yang membuat kelas ini bisa digunakan sebagai JUnit Rule.
 *
 */

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    //ada 2 jenis testdispatcher yang bisa digunakan:
    // UnconfinedTestDispatcher untuk test yang tidak membutuhkan pause/resume alias otomatis langsung eksekusi.
    // StandardTestDispatcher : Untuk test yang membutuhkan pause/resume.
) : TestWatcher() {

    /**
     * Fungsi starting dan finished berfungsi sama seperti ketika Anda menuliskan kode di @Before dan @After.
     */
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}