package com.noble.activity.artifactcards

import android.content.Context

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