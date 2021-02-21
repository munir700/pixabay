package com.data.enums

import com.data.utils.ImageConstants

enum class ImageType(val type: String) {
    ALL(ImageConstants.ALL),
    PHOTO(ImageConstants.PHTOT),
    ILLUSTRATION(ImageConstants.ILLUSTRATION),
    VECTOR(ImageConstants.VECTOR),
    DEFAULT(ImageConstants.DEFAULT)
}