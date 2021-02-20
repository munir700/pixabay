package com.data.local.db.searchimage

import androidx.paging.DataSource
import com.data.local.models.Photo

interface SearchImageHelper {

    fun insertImage(photo: Photo)

    fun insertImages(photos: List<Photo>)

    fun searchImageByTitle(searchQuery: String): List<Photo>?

    fun searchImagesDataSource(): DataSource.Factory<Int, Any>?
}