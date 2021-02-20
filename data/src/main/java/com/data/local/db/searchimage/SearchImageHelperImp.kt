package com.data.local.db.searchimage

import androidx.paging.DataSource
import com.data.local.db.subhelper.BaseDbHelper
import com.data.local.models.Photo

abstract class SearchImageHelperImp : BaseDbHelper(), SearchImageHelper {
    override fun insertImage(photo: Photo) {
        mAppDatabase?.searchImageDao()?.insert(photo)
    }

    override fun insertImages(photos: List<Photo>) {
        mAppDatabase?.searchImageDao()?.insertAll(photos)
    }

    override fun searchImageByTitle(searchQuery: String): List<Photo>? {
        return mAppDatabase?.searchImageDao()?.searchImageByTitle(searchQuery)
    }

    override fun searchImagesDataSource(): DataSource.Factory<Int, Any>? {

        return mAppDatabase?.searchImageDao()?.searchImagesDataSource()?.map {
            it as Any
        }
    }
}