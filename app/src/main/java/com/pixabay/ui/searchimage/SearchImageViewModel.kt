package com.pixabay.ui.searchimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.utils.StringUtils
import com.data.local.models.Photo
import com.data.local.models.PhotoSearchResponse
import com.data.local.models.State
import com.data.repository.SearchRepository
import com.pixabay.BuildConfig
import com.squareup.moshi.Moshi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class SearchImageViewModel @Inject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {

    var isPerformingNextQuery = false

    private var isPerformingQuery = false

    private var pageNumber: Int = 1

    private val queryLiveData = MutableLiveData<String>()

    private val _imagesUiLiveData = MutableLiveData<State<String>>()

    val imagesUiLiveData: LiveData<State<String>>
        get() = _imagesUiLiveData

    private val _imagesLiveData = MutableLiveData<List<Photo>>()

    val imagesLiveData: LiveData<List<Photo>>
        get() = _imagesLiveData

    private var results = ArrayList<Photo>()

    fun parseAndSaveImages(inputStream: InputStream) {
        viewModelScope.launch(Dispatchers.IO) {
            _imagesUiLiveData.postValue(State.loading())
            try {
                val result = parseImages(inputStream)
                result?.let {
                    insertSearchImages(it)
                    _imagesUiLiveData.postValue(State.success(""))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseImages(inputStream: InputStream): List<Photo>? {
        val searchImageString = StringUtils.inputStreamToString(inputStream)
        val moshi = Moshi.Builder().build()
        val adapter = moshi.adapter(PhotoSearchResponse::class.java)
        return adapter.fromJson(searchImageString)?.hits
    }

    private fun insertSearchImages(list: List<Photo>) {
        viewModelScope.launch(Dispatchers.IO) {
            searchRepository.insertSearchImage(list)
            _imagesUiLiveData.postValue(State.success(""))
        }
    }

    fun getImagesRequest(query: String, pageNumber: Int = 1) {
        isPerformingQuery = true
        if (!query.equals(queryLiveData.value)) {
            results.clear()
        }
        queryLiveData.value = query
        viewModelScope.launch(Dispatchers.Default) {
            _imagesUiLiveData.postValue(State.loading())
            try {
                val result = searchRepository.getSearchImagesRequest(
                    BuildConfig.API_KEY,
                    query,
                    page = pageNumber
                )
                val apiResponse = result.body()
                isPerformingQuery = false
                _imagesUiLiveData.postValue(State.success("Data loaded"))
                apiResponse?.let {
                    results.addAll(State.success(it.hits).data)
                    _imagesLiveData.postValue(results)
                } ?: kotlin.run {
                    _imagesUiLiveData.postValue(State.error("No Photo Data Found!"))
                }
            } catch (e: Exception) {
                isPerformingQuery = false
                _imagesUiLiveData.postValue(State.error(e.message.toString()))
            }
        }
    }

    fun searchNextPage() {
        if (!isPerformingQuery) {
            isPerformingNextQuery = true
            pageNumber += 1
            getImagesRequest(queryLiveData.value!!, pageNumber)
        }
    }
}