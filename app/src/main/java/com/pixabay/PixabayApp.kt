package com.pixabay

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PixabayApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
    }
}