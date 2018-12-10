package com.noble.activity.artifactcards.prefs

import android.content.Context
import android.content.SharedPreferences

class RefreshPrefs(context: Context) {
    private val PREFS_FILENAME = "refresh_prefs"

    private val REFRESH_DAY = "refresh_date"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var refreshCards: Int
        get() = prefs.getInt(REFRESH_DAY, 0)
        set(value) = prefs.edit().putInt(REFRESH_DAY, value).apply()

    fun isRefreshNeeded()  = refreshCards < 380


    fun updateRefreshDay(countCards: Int) {
        refreshCards = countCards
    }

}