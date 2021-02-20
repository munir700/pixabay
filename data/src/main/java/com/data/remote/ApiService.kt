package com.data.remote

import com.data.local.models.PhotoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service to fetch Data using end point [API_URL].
 */
interface ApiService {

    @GET("api/")
    suspend fun searchImages(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("image_type") imageType: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PhotoSearchResponse>

}