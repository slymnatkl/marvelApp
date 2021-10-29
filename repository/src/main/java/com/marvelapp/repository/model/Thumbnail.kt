package com.marvelapp.repository.model

import com.google.gson.annotations.SerializedName

open class Thumbnail{

    @SerializedName("path")
    val path: String? = null

    @SerializedName("extension")
    val extension: String? = null

    open fun getThumbnailUrl(): String? {

        return "$path.$extension"
    }
}
