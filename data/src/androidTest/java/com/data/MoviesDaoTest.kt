package com.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.data.enums.ImageType
import com.data.local.db.AppDatabase
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesDaoTest {

    private lateinit var imagesDatabase: AppDatabase

    @Before
    fun init() {
        imagesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_InsertAndSelectImages() {

        imagesDatabase.searchImageDao().insertAll(TestData.getTestImages())
        val dbImages = imagesDatabase.searchImageDao().getAllSearchImages()

        MatcherAssert.assertThat(dbImages, CoreMatchers.equalTo(TestData.getTestImages()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_MoviesByUser() {

        imagesDatabase.searchImageDao().insertAll(TestData.getTestImages())

        val dbImages = imagesDatabase.searchImageDao().searchImageByUser("enriquelopezgarre")

        MatcherAssert.assertThat(
            dbImages?.get(0),
            CoreMatchers.equalTo(TestData.getTestImages()[0])
        )
    }

    @Test
    @Throws(InterruptedException::class)
    fun test_MoviesByType() {

        imagesDatabase.searchImageDao().insertAll(TestData.getTestImages())

        val dbImages = imagesDatabase.searchImageDao().searchImageByType(ImageType.PHOTO.type)

        MatcherAssert.assertThat(
            dbImages?.get(0),
            CoreMatchers.equalTo(TestData.getTestImages()[0])
        )

    }

    @Test
    @Throws(InterruptedException::class)
    fun test_MoviesByTags() {

        imagesDatabase.searchImageDao().insertAll(TestData.getTestImages())

        val dbImages = imagesDatabase.searchImageDao().searchImageByTags("city, rain, glass")

        MatcherAssert.assertThat(
            dbImages?.get(0),
            CoreMatchers.equalTo(TestData.getTestImages()[0])
        )

    }

    @After
    fun cleanup() {
        imagesDatabase.close()
    }
}
