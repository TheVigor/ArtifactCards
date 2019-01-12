package com.noble.activity.artifactcards.prefs

import android.content.Context
import android.content.SharedPreferences

class DecksPrefs(context: Context) {

    companion object {
        private val deckCodes = mutableSetOf(
            "ADCJSIMJLkCChFLi3hdQqzdAUGFgoqbaAJDBH8BdQFCKgEvAUZhY2UgU21hc2hlcg__",
            "ADCJSYJNrgCWQyTAy29ApIMkkFBQ6EDiEQiAYKTkZSNTW9ubyBCbHVl",
            "ADCJWEQNrgCEY8JT3hdvd8BH0qTIwEDiqoBh4ZZgmQBYQFFY29ub21pYyBDb250cm9s",
            "ADCJcUQL7kCQYsNCbhdgXfdAUqrAZqfpQNIhI9oAXIBSHlwZWQgQmx1ZS9HcmVlbg__",

            "ADCJVMavrgCygUGzgRZoLsCTopHlKYBZAFBBE1TuQFbrQIWUmVkL0dyZWVuIE1pZHJhbmdlIFN1c3RhaW4_",
            "ADCJbIIJLkCSpHLBMsFZrsChkKKm0FsAUZVQ1ESSyoCXBBdGlRlbGVwb3J0",
            "ADCJbUUMbgCXsEFhMgEcLsCi55Bn4wZBQxMCEkBPgGTbAGNVS9HIFJlZ2VuIENyZWVwIFJhbXA_",
            "ADCJSUPNrgCl84E1gVEuF2g3gGbQUZlAZ8SUaECQXUBRqQBSlJlZCBCbHVlIEJ1ZGdldA__",
            "ADCJVIg7bkCBQGRywRLprsCgYWMm6gCsQGBVCEBhw2CUmVkL0JsYWNrIC0gcGVyZmVjdCBzdGFydGVyIGRlY2s_"

        )
    }

    private val PREFS_FILENAME = "decks_prefs_updated"
    private val CURRENT_DECKS = "current_decks"

    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var refreshCards: MutableSet<String>
        get() = prefs.getStringSet(CURRENT_DECKS, deckCodes)
        set(value) = prefs.edit().putStringSet(CURRENT_DECKS, value).apply()

}