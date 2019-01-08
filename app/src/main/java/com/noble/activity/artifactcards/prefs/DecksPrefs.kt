package com.noble.activity.artifactcards.prefs

import android.content.Context
import android.content.SharedPreferences

class DecksPrefs(context: Context) {

    companion object {
        private val deckCodes = mutableSetOf(
            "ADCJQUQI30zuwEYg2ABeF1Bu94BmWIBTEkLtAKlAZakAYmHh0JsdWUvUmVkIEV4YW1wbGU_",
            "ADCJRwSJX2Dc7wBEAN4XUFBcN0BQmQBQWABRCgBCgN0AWUBbQFDbwEISEJsdWUvQmxhY2sgQ29udHJvbA__",
            "ADCJcURIH0De7sBKAGQeF1BQWbdAVhHRwFIMQIECG0CTgIfRlBCdQFSZWQvR3JlZW4gQnJhd2xlcg__",
            "ADCJY8UNrgC0QVIh8kEubwCZQKTkBasAYgHhpmmAZOBKEJsYWNrL0JsdWUpIFBheWJhY2s_",
            "ADCJRkQfrgCCQ0LlHpdpt0BhoaPhJGgAo1SU6UCowNCL0cgUGF1cGVyIEFnZ3Jv"
        )
    }

    private val PREFS_FILENAME = "decks_prefs"
    private val CURRENT_DECKS = "current_decks"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var refreshCards: MutableSet<String>
        get() = prefs.getStringSet(CURRENT_DECKS, deckCodes)
        set(value) = prefs.edit().putStringSet(CURRENT_DECKS, value).apply()

}