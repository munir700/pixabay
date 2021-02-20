package com.data.manager

import com.data.local.db.DbHelper
import com.data.remote.ApiService


interface DataManager {

    fun getDbHelper(): DbHelper

    fun getApiHelper(): ApiService
}