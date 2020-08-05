package com.example.cleanarchitecture.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by tamayan on 2017/12/10.
 */

object DateConverter {

    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    fun toDate(value: String): Date? {
        return format.parse(value)
    }

    fun toDateString(date: Date): String {
        return format.format(date)
    }
}