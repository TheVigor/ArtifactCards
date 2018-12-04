package com.noble.activity.artifactcards

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class RefreshPrefs(context: Context) {
    private val PREFS_FILENAME = "refresh_prefs"

    private val REFRESH_DAY = "refresh_date"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var refreshDay: Int
        get() = prefs.getInt(REFRESH_DAY, 0)
        set(value) = prefs.edit().putInt(REFRESH_DAY, value).apply()

    fun isRefreshNeeded(): Boolean {
        val today = Calendar.getInstance().get(Calendar.DATE)
        val lastSavedDay = refreshDay
        if (lastSavedDay != today)
            return true

        return false
    }

    fun updateRefreshDay() {
        refreshDay = Calendar.getInstance().get(Calendar.DATE)
    }

}