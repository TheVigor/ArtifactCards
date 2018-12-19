package com.noble.activity.artifactcards.app

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModelProviders
import com.noble.activity.artifactcards.prefs.RefreshPrefs

val refreshPrefs: RefreshPrefs by lazy {
    App.refreshPrefs!!
}

val app: App by lazy {
    App.INSTANCE!!
}

val searchData: MutableLiveData<String> by lazy {
    App.searchData
}
class App : android.app.Application() {
    override fun onCreate() {
        refreshPrefs = RefreshPrefs(applicationContext)
        searchData = MutableLiveData()
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        var INSTANCE: App? = null
        var refreshPrefs: RefreshPrefs? = null

        lateinit var searchData: MutableLiveData<String>
    }
}