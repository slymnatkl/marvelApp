package com.marvelapp.repository.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.marvelapp.repository.helper.Helper
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Date(
    @SerializedName("type") val type: String? = null,
    @SerializedName("date") val date: String? = null
): Parcelable
{
    fun getDateString(): String {

        return try {
            Helper.dateToString(Helper.stringToDate(date!!,"yyyy-MM-dd'T'HH:mm:ssZ")!!, "yyyy-MM-dd")
        } catch (e: Exception){
            e.printStackTrace()
            ""
        }
    }
}
