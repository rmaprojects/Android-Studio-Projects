package com.dicoding.newsapp.ui.list

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.dicoding.newsapp.R
import com.dicoding.newsapp.data.local.room.utils.JsonConverter
import com.dicoding.newsapp.data.remote.retrofit.ApiConfig
import com.dicoding.newsapp.utils.EspressoIdlingResource
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    //menyiapkan mockwebserver:

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start(8000)
        ApiConfig.BASE_URL = "http://127.0.0.1:8080/"
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun getHeadline_Success() {
        val bundle = Bundle()
        bundle.putString(NewsFragment.ARG_TAB, NewsFragment.TAB_NEWS)
        launchFragmentInContainer<NewsFragment>(bundle, R.style.Theme_News)

        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(JsonConverter.readStringFromFile("success_response.json"))
        mockWebServer.enqueue(mockResponse)

        //Check if data is match

        onView(withId(R.id.rv_news))
            .check(matches(isDisplayed()))

        onView(withText("Inti Bumi Mendingin Lebih Cepat, Pertanda Apa? - detikInet"))
            .check(matches(isDisplayed()))

        //Scroll to check texts
        onView(withId(R.id.rv_news))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText("Perjalanan Luar Angkasa Sebabkan Anemia - CNN Indonesia"))
                )
            )
    }

    @Test
    fun getHeadlineNews_Error() {
        val bundle = Bundle()
        bundle.putString(NewsFragment.ARG_TAB, NewsFragment.TAB_NEWS)
        launchFragmentInContainer<NewsFragment>(bundle, R.style.Theme_News)
        val mockResponse = MockResponse()
            .setResponseCode(500)
        mockWebServer.enqueue(mockResponse)
        onView(withId(R.id.rv_news))
            .check(matches(isDisplayed()))
        onView(withText("Oops.. something went wrong."))
            .check(matches(isDisplayed()))
    }
}

/**
 *
 * Kita akan menggunakan MockWebServer untuk membuat server tiruan.
 * Tujuannya adalah untuk menampilkan data yang statis.
 * Tidak hanya itu saja, kita bisa melakukan simulasi apabila terdapat error dari sisi server.
 *
*/