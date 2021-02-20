package com.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.data.local.db.dao.SearchImageDao
import com.data.local.models.Photo

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchImageDao(): SearchImageDao
}