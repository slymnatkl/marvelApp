package com.marvelapp.repository.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse <T> {

    @SerializedName("code")
    var code: Int? = null

    @SerializedName("status")
    var status: String? = null

    @SerializedName("data")
    var data: T? = null
}
