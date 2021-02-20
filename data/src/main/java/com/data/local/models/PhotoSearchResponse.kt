package com.data.local.models

import com.squareup.moshi.Json

data class PhotoSearchResponse(

    @Json(name = "total")
    val total: Int = 0,

    @Json(name = "totalHits")
    val totalHits: Int = 0,

    @Json(name = "hits")
    val hits: List<Photo>
)