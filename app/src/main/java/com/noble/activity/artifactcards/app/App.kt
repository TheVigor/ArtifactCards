package com.noble.activity.artifactcards.app

import android.content.Context
import com.noble.activity.artifactcards.prefs.RefreshPrefs

val refreshPrefs: RefreshPrefs by lazy {
    App.refreshPrefs!!
}

val app: App by lazy {
    App.INSTANCE!!
}

class App : android.app.Application() {
    override fun onCreate() {
        refreshPrefs = RefreshPrefs(applicationContext)
        super.onCreate()
        INSTANCE = this
    }

    companion object {

        var INSTANCE: App? = null
        var refreshPrefs: RefreshPrefs? = null

    }
}