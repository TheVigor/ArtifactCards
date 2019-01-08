package com.noble.activity.artifactcards.prefs

import android.content.Context
import android.content.SharedPreferences

class DecksPrefs(context: Context) {

    companion object {
        private val deckCodes = mutableSetOf(
            "ADCJSIMJLkCChFLi3hdQqzdAUGFgoqbaAJDBH8BdQFCKgEvAUZhY2UgU21hc2hlcg__",
            "ADCJSYJNrgCWQyTAy29ApIMkkFBQ6EDiEQiAYKTkZSNTW9ubyBCbHVl"
        )
    }

    private val PREFS_FILENAME = "decks_prefs"
    private val CURRENT_DECKS = "current_decks"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var refreshCards: MutableSet<String>
        get() = prefs.getStringSet(CURRENT_DECKS, deckCodes)
        set(value) = prefs.edit().putStringSet(CURRENT_DECKS, value).apply()

}