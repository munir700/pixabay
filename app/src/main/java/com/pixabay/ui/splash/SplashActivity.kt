package com.pixabay.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.core.utils.launchActivity
import com.pixabay.R
import com.pixabay.ui.searchimage.SearchImageActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launch {
            delay(1000)
            launchActivity(this@SplashActivity, SearchImageActivity::class.java, true)
        }
    }
}