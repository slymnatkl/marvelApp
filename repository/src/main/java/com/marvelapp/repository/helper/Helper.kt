package com.marvelapp.repository.helper

import java.math.BigInteger
import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.*

object Helper {

    fun md5(input: String) = hashString("MD5", input)

    private fun hashString(type: String, input: String): String {

        val md = MessageDigest.getInstance(type)
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun dateToString(date: Date, format: String): String {

        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
        return dateFormatter.format(date)
    }

    fun stringToDate(dateStr: String, format: String): Date? {
        return SimpleDateFormat(format, Locale.getDefault()).parse(dateStr)
    }
}