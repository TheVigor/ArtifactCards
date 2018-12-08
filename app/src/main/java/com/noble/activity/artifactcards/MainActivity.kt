package com.noble.activity.artifactcards

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    private var mainFragment: MainFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.container)

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, mainFragment!!, "MainFragment")
                .commit()
        }
    }
}
