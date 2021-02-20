package com.data.manager

import com.data.local.db.DbHelper
import com.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManagerImpl @Inject constructor(
    private val apiService: ApiService,
    private val dbHelper: DbHelper
) : DataManager {
    override fun getDbHelper(): DbHelper {
        return dbHelper
    }

    override fun getApiHelper(): ApiService {
        return apiService
    }
}