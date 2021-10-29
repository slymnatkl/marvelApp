package com.marvelapp.repository.model

import com.google.gson.annotations.SerializedName

open class Character{

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("name")
    val name: String? = null

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null
}
