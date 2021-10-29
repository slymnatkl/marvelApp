package com.marvelapp.repository.model

import com.google.gson.annotations.SerializedName
import com.marvelapp.repository.utils.Utils

open class Date{

    @SerializedName("type")
    val type: String? = null

    @SerializedName("date")
    val date: String? = null

    open fun getDateString(): String? {

        try {
            return Utils.dateToString(Utils.stringToDate(date!!,"yyyy-MM-dd'T'HH:mm:ssZ")!!, "yyyy-MM-dd")
        }
        catch (e: Exception){
            e.printStackTrace()
            return ""
        }
    }
}
