package com.hisham.ctintegrationsample.core

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.AnyRes

fun Context.getIdByName(name: String, type: String = "string"): Int {
    return resources.getIdentifier(name, type, packageName)
}

fun Context.getStringIdByName(name: String): Int {
    return getIdByName(name)
}

fun Context.getColourIdByName(name: String): Int {
    return getIdByName(name, "color")
}

fun Context.getResourceNameById(@AnyRes resId: Int): String {
    return resources.getResourceEntryName(resId) ?: ""
}

fun SharedPreferences.getStringOrDefault(key: String, defaultValue: String): String {
    return getString(key, defaultValue) ?: defaultValue
}