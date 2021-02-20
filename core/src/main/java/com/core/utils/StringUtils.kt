package com.core.utils

import android.text.TextUtils
import java.io.IOException
import java.io.InputStream

object StringUtils {

    fun inputStreamToString(inputStream: InputStream): String {
        try {
            val bytes = ByteArray(inputStream.available())
            inputStream.read(bytes, 0, bytes.size)
            return String(bytes)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        return ""
    }

    @JvmStatic
    fun getTags(tags: String?): String {
        return when {
            tags == null -> ""
            tags.contains(",") -> {
                val splitTags = tags.toUpperCase().split(", ")
                TextUtils.join(" - ", splitTags)
            }
            else -> tags
        }
    }

    @JvmStatic
    fun byUser(userName: String): String {
        return "By: $userName"
    }

    @JvmStatic
    fun getCommentsAsString(comments: Int): String {
        return comments.toString()
    }

    @JvmStatic
    fun getLikesAsString(likes: Int): String {
        return likes.toString()
    }

    @JvmStatic
    fun getFavoritesAsString(favorites: Int): String {
        return favorites.toString()
    }
}
