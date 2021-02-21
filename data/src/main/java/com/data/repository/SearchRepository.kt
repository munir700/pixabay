package com.data.repository

import androidx.paging.DataSource
import com.data.enums.ImageType
import com.data.local.models.Photo
import com.data.local.models.PhotoSearchResponse
import com.data.manager.DataManager
import com.data.utils.ITEM_PER_PAGE
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(private val dataManager: DataManager) {
    fun insertSearchImage(list: List<Photo>) {
        dataManager.getDbHelper().insertImages(list)
    }

    fun searchImagesDataSource(): DataSource.Factory<Int, Any>? {
        return dataManager.getDbHelper().searchImagesDataSource()
    }

    fun searchImageByTag(tag: String): List<Any>? {
        return dataManager.getDbHelper().searchImageByTags(tag)
    }

    suspend fun getSearchImagesRequest(
        apiKey: String,
        q: String,
        imageType: String = ImageType.ALL.type,
        page: Int,
        perPage: Int = ITEM_PER_PAGE
    ): Response<PhotoSearchResponse> {
        return dataManager.getApiHelper().searchImages(
            key = apiKey,
            q = q,
            imageType = imageType,
            page = page,
            perPage = perPage
        )
    }
}