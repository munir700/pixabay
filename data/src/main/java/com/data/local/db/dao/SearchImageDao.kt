package com.data.local.db.dao

import androidx.paging.DataSource
import androidx.room.*
import com.data.local.db.subhelper.BaseDao
import com.data.local.models.Photo

@Dao
interface SearchImageDao : BaseDao<Photo> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(photos: List<Photo>)

    @Query("SELECT * FROM search_image ORDER BY id ASC")
    fun getAllSearchImages(): List<Photo>

    @Query("SELECT * FROM search_image ORDER BY id ASC")
    fun searchImagesDataSource(): DataSource.Factory<Int, Photo>

    @Query("SELECT * FROM search_image WHERE user LIKE '%' || :user")
    fun searchImageByUser(user: String): List<Photo>?

    @Query("SELECT * FROM search_image WHERE type LIKE '%' || :type")
    fun searchImageByType(type: String): List<Photo>?

    @Query("SELECT * FROM search_image WHERE tags LIKE '%' || :tag")
    fun searchImageByTags(tag: String): List<Photo>?
}