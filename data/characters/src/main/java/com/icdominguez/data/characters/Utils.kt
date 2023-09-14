package com.icdominguez.data.characters

import android.util.Log

fun getPageIntFromUrl(url: String): Int? {
    return try {
        url.substringAfterLast("=").toInt()
    } catch (e: Exception) {
        e.message?.let { Log.d("Converting url", it) }
        null
    }
}

fun getEpisodeFromUrl(url: String): Int? {
    return try {
        url.substringAfterLast("/").toInt()
    } catch (e: java.lang.Exception) {
        e.message?.let { Log.d("converting url", it) }
        null
    }
}
