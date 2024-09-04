package com.example.client.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun Long.toTime(): String {
    val date = Date(this)
    val format = if (this.isCurrentDay()) SimpleDateFormat("HH:mm") else SimpleDateFormat("dd-MM-yyyy")
    return format.format(date)
}

@SuppressLint("SimpleDateFormat")
fun Long.toTimeV2(): String {
    val date = Date(this)
    val format = SimpleDateFormat("HH:mm")
    return format.format(date)
}

fun Long.isSameDay(time: Long): Boolean {
    val cal1: Calendar = Calendar.getInstance()
    val cal2: Calendar = Calendar.getInstance()
    cal1.time = Date(this)
    cal2.time = Date(time)
    return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
            cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
}

fun Long.isCurrentDay(): Boolean {
    return isSameDay(System.currentTimeMillis())
}