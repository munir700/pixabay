package com.pixabay.ui.searchimage

import android.content.Intent
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.core.utils.setVisibility
import com.data.local.models.Photo
import com.data.local.models.State
import com.pixabay.BR
import com.pixabay.R
import com.pixabay.databinding.FragmentSearchImageBinding
import com.pixabay.ui.base.BaseFragment
import com.pixabay.ui.searchimage.adapter.PhotoListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchImageFragment :
    BaseFragment<SearchImageViewModel, FragmentSearchImageBinding>(R.layout.fragment_search_image) {

    private val photoListAdapter: PhotoListAdapter by lazy {
        PhotoListAdapter { photo ->
            run {
                shareToApps(photo.tags, photo.webformatURL)
            }
        }
    }

    override val viewModel: SearchImageViewModel by viewModels()

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun onInitDataBinding() {
        updateProgress()
        searchImages()
        viewModel.getImagesRequest("");
        bindings.imagesRecycler.adapter = photoListAdapter
        bindings.imagesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastPosition = layoutManager.findLastVisibleItemPosition()
                if (lastPosition == photoListAdapter.itemCount - 1) {
                    bindings.imagesRecycler.post {
                        viewModel.isPerformingNextQuery = true
                        viewModel.searchNextPage()
                    }
                }
            }
        })

        viewModel.imagesLiveData.observe(viewLifecycleOwner, Observer {
            val copy: List<Photo> = ArrayList(it)
            photoListAdapter.submitList(copy)
        })
    }


    private fun updateProgress() {
        viewModel.imagesUiLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Success -> {
                    bindings.progressbar.setVisibility(false)
                }
                is State.Error -> {
                    bindings.progressbar.setVisibility(false)
                }
                is State.Loading -> {
                    bindings.progressbar.setVisibility(true)
                }
            }
        })
    }

    private fun searchImages() {
        bindings.imageSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("query", "input $query")
                query?.let {
                    viewModel.getImagesRequest(it)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
        bindings.imageSearch.setOnCloseListener {
            viewModel.getImagesRequest("");
            true
        }
    }

    private fun shareToApps(tag: String, imageUrl: String) {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, imageUrl)

            // (Optional) Here we're setting the title of the content
            putExtra(Intent.EXTRA_TITLE, tag)

        }, null)
        startActivity(share)
    }
}