package com.marvelapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    @SerializedName("path") val path: String? = null,
    @SerializedName("extension") val extension: String? = null
): Parcelable
{
    fun getThumbnailUrl(): String {
        return "$path.$extension"
    }
}
