package com.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.data.enums.ImageType
import com.data.utils.ITEM_PER_PAGE
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(JUnit4::class)
class ApiServiceTest {

    @get:Rule
    var instantExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var apiService: ApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun test_getImages() = runBlocking {
        enqueueResponse("images.json")
        val images = apiService.searchImages(
            key = "20311183-3e8b4c71f83da2bec7587992c",
            q = "flowers",
            imageType = ImageType.PHOTO.type,
            page = 1,
            perPage = ITEM_PER_PAGE
        ).body()

        assertThat(images, notNullValue())
        assertThat(images?.hits?.get(0)?.user, `is`("enriquelopezgarre"))
        assertThat(images?.hits?.get(0)?.userId, `is`(3764790))
        assertThat(images?.hits?.get(0)?.id, `is`(6018511))
        assertThat(images?.hits?.get(0)?.type, `is`(ImageType.PHOTO.type))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
