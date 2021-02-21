package com.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "search_image", indices = [Index("primaryId")])
data class Photo(
    @PrimaryKey(autoGenerate = true) val primaryId: Long = 0,

    @ColumnInfo(name = "id")
    @Json(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "pageURL")
    @Json(name = "pageURL")
    val pageURL: String = "",

    @ColumnInfo(name = "type")
    @Json(name = "type")
    val type: String = "",


    @ColumnInfo(name = "tags")
    @Json(name = "tags")
    val tags: String = "",

    @ColumnInfo(name = "previewURL")
    @Json(name = "previewURL")
    val previewURL: String = "",

    @ColumnInfo(name = "previewWidth")
    @Json(name = "previewWidth")
    val previewWidth: Int = 0,

    @ColumnInfo(name = "previewHeight")
    @Json(name = "previewHeight")
    val previewHeight: Int? = 0,

    @ColumnInfo(name = "webformatURL")
    @Json(name = "webformatURL")
    val webformatURL: String = "",

    @ColumnInfo(name = "webformatWidth")
    @Json(name = "webformatWidth")
    val webformatWidth: Int = 0,

    @ColumnInfo(name = "webformatHeight")
    @Json(name = "webformatHeight")
    val webformatHeight: Int = 0,

    @ColumnInfo(name = "largeImageURL")
    @Json(name = "largeImageURL")
    val largeImageURL: String? = "",

    @ColumnInfo(name = "fullHDURL")
    @Json(name = "fullHDURL")
    val fullHDURL: String = "",

    @ColumnInfo(name = "imageURL")
    @Json(name = "imageURL")
    val imageURL: String = "",

    @ColumnInfo(name = "imageWidth")
    @Json(name = "imageWidth")
    val imageWidth: Int = 0,

    @ColumnInfo(name = "imageHeight")
    @Json(name = "imageHeight")
    val imageHeight: Int = 0,

    @ColumnInfo(name = "imageSize")
    @Json(name = "imageSize")
    val imageSize: Int = 0,

    @ColumnInfo(name = "views")
    @Json(name = "views")
    val views: Int = 0,

    @ColumnInfo(name = "downloads")
    @Json(name = "downloads")
    val downloads: Int = 0,

    @ColumnInfo(name = "favorites")
    @Json(name = "favorites")
    val favorites: Int = 0,

    @ColumnInfo(name = "likes")
    @Json(name = "likes")
    val likes: Int = 0,

    @ColumnInfo(name = "comments")
    @Json(name = "comments")
    val comments: Int = 0,

    @ColumnInfo(name = "user_id")
    @Json(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "user")
    @Json(name = "user")
    val user: String = ""
)