package com.marvelapp.repository.utils

import java.math.BigInteger
import java.security.MessageDigest

object Utils {

    fun md5(input: String) = hashString("MD5", input)

    private fun hashString(type: String, input: String): String {

        val md = MessageDigest.getInstance(type)
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}