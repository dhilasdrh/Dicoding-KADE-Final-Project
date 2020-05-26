@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package com.dhilasadrah.kadesubmission5.util

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun String.simpleDate(oldFormat: String="dd/MM/yy", newFormat: String = "EEE, d MMM yyyy"): String {
    val date = SimpleDateFormat(oldFormat, Locale.US).parse(this)
    val dateFormat = SimpleDateFormat(newFormat, Locale.US)
    return dateFormat.format(date)
}

fun String.simpleTime(oldFormat: String="HH:mm:ss", newFormat: String = "hh:mm aa"): String {
    val time = SimpleDateFormat(oldFormat, Locale.US).parse(this)
    val timeFormat = SimpleDateFormat(newFormat, Locale.US)
    return timeFormat.format(time)
}

fun String.replaceSpace(oldValue: String = " ", newValue: String = "%20"): String {
    return this.replace(oldValue, newValue)
}

fun String.parseLineup(oldValue: String = "; ", newValue: String = ";\n" ): String {
    return this.replace(oldValue, newValue)
}

fun String.parseDetail(oldValue: String = ";", newValue: String = ";\n"): String {
    return this.replace(oldValue, newValue)
}