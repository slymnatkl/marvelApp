package com.marvelapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
open class Comic: Parcelable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("title")
    val title: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("thumbnail")
    val thumbnail: Thumbnail? = null

    @SerializedName("dates")
    val results: List<Date>? = null

    fun getFocDate(): String?{

        results?.let {
            it.forEach { date ->
                if(date.type.equals("focDate"))
                    return date.getDateString()
            }
        }
        return null
    }
}
