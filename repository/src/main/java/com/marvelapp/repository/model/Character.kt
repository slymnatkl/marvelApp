package com.marvelapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Character: Parcelable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null
}
