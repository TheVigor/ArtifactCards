package com.noble.activity.artifactcards.app

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.prefs.RefreshPrefs
import com.noble.activity.artifactcards.utils.COLOR_BLACK
import com.noble.activity.artifactcards.utils.COLOR_BLUE
import com.noble.activity.artifactcards.utils.COLOR_GREEN
import com.noble.activity.artifactcards.utils.COLOR_RED

val refreshPrefs: RefreshPrefs by lazy {
    App.refreshPrefs!!
}

val app: App by lazy {
    App.INSTANCE!!
}

val searchData: MutableLiveData<String> by lazy {
    App.searchData
}
val colorData: MutableLiveData<String> by lazy {
    App.colorData
}


val colorFilter: MutableList<String> by lazy {
    App.colorFilter
}

val cardDeck: CardDeck by lazy {
    App.cardDeck
}


class App : android.app.Application() {
    override fun onCreate() {
        refreshPrefs = RefreshPrefs(applicationContext)
        colorFilter = mutableListOf(COLOR_RED, COLOR_GREEN, COLOR_BLUE, COLOR_BLACK)
        searchData = MutableLiveData()
        colorData = MutableLiveData()

        cardDeck = CardDeck("", listOf(), listOf(), mutableMapOf())


        super.onCreate()
        INSTANCE = this
    }

    companion object {
        var INSTANCE: App? = null
        var refreshPrefs: RefreshPrefs? = null

        lateinit var searchData: MutableLiveData<String>
        lateinit var colorData: MutableLiveData<String>

        lateinit var colorFilter: MutableList<String>

        lateinit var cardDeck: CardDeck

    }
}