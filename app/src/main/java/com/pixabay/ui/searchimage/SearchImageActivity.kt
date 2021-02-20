package com.pixabay.ui.searchimage

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.pixabay.BR
import com.pixabay.R
import com.pixabay.databinding.ActivitySearchImageBinding
import com.pixabay.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_search_image.*

@AndroidEntryPoint
class SearchImageActivity() :
    BaseActivity<SearchImageViewModel, ActivitySearchImageBinding>(R.layout.activity_search_image) {

    private lateinit var navController: NavController

    override fun getBindingVariable(): Int = BR._all

    override val viewModel: SearchImageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController(R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}