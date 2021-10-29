package com.marvelapp.repository.network.response

import com.google.gson.annotations.SerializedName
import com.marvelapp.repository.model.Character

open class CharactersResponse{

    @SerializedName("offset")
    val offset: Int? = null

    @SerializedName("limit")
    val limit: Int? = null

    @SerializedName("total")
    val total: Int? = null

    @SerializedName("count")
    val count: Int? = null

    @SerializedName("results")
    val results: List<Character>? = null
}
