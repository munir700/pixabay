package com.data.local.db

import com.data.local.db.searchimage.SearchImageHelperImp
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This class is a helper to get access all Database related queries.
 * It is a single point of interaction and sub helpers are abstract.
 */
@Singleton
class DbHelperImpl @Inject constructor(appDatabase: AppDatabase) : SearchImageHelperImp(),
    DbHelper {
    init {
        mAppDatabase = appDatabase
    }
}