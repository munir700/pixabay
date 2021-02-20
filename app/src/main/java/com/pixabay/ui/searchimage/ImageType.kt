package com.pixabay.ui.searchimage

import com.pixabay.utils.ImageConstants

enum class ImageType(val type: String) {
    ALL(ImageConstants.ALL),
    PHOTO(ImageConstants.PHTOT),
    ILLUSTRATION(ImageConstants.ILLUSTRATION),
    VECTOR(ImageConstants.VECTOR),
    DEFAULT(ImageConstants.DEFAULT)
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}