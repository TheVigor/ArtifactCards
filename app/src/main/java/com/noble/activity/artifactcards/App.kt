package com.noble.activity.artifactcards

import android.content.Context
import android.support.multidex.MultiDex
import com.blankj.utilcode.util.Utils

val refreshPrefs: RefreshPrefs by lazy {
    App.refreshPrefs!!
}

class App : android.app.Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        refreshPrefs = RefreshPrefs(applicationContext)
        super.onCreate()
        INSTANCE = this

        Utils.init(App.get()!!)
        com.ruzhan.lion.App.setApp(this)

    }

    companion object {

        private var INSTANCE: App? = null
        var refreshPrefs: RefreshPrefs? = null

        fun get(): App? {
            return INSTANCE
        }
    }
}