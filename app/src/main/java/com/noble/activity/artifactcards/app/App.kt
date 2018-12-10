package com.noble.activity.artifactcards.app

import android.content.Context
import com.noble.activity.artifactcards.prefs.RefreshPrefs

val refreshPrefs: RefreshPrefs by lazy {
    App.refreshPrefs!!
}

class App : android.app.Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        refreshPrefs = RefreshPrefs(applicationContext)
        super.onCreate()
        INSTANCE = this


    }

    companion object {

        private var INSTANCE: App? = null
        var refreshPrefs: RefreshPrefs? = null

        fun get(): App? {
            return INSTANCE
        }
    }
}