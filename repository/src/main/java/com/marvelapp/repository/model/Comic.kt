package com.marvelapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Comic(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("thumbnail") val thumbnail: Thumbnail? = null,
    @SerializedName("dates") val dates: List<Date>? = null,
    @SerializedName("extension") val extension: String? = null
): Parcelable
{
    fun getFocDate(): String?{

        return searchDate("focDate")
    }

    fun getSaleDate(): String?{

        return searchDate("onsaleDate")
    }

    private fun searchDate(filter: String): String?{

        dates?.let {
            it.forEach { date ->
                if(date.type.equals(filter))
                    return date.getDateString()
            }
        }
        return null
    }
}
