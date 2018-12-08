package com.noble.activity.artifactcards

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ArtifactCardDetailActivity : AppCompatActivity() {

    companion object {

        private const val NEW_ID = "newId"
        private const val NEW_TITLE = "newTitle"
        private const val NEW_URL = "newUrl"

        @JvmStatic
        fun launch(activity: Activity, newId: String, title: String, imageUrl: String) {
            val intent = Intent(activity, ArtifactCardDetailActivity::class.java)
            intent.putExtra(NEW_ID, newId)
            intent.putExtra(NEW_TITLE, title)
            intent.putExtra(NEW_URL, imageUrl)
            activity.startActivity(intent)
        }
    }

    private lateinit var newId: String
    private lateinit var title: String
    private lateinit var imageUrl: String

    private var artifactCardDetailFragment: ArtifactCardDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.container)

        newId = intent.getStringExtra(NEW_ID)
        title = intent.getStringExtra(NEW_TITLE)
        imageUrl = intent.getStringExtra(NEW_URL)

        if (artifactCardDetailFragment == null) {
            artifactCardDetailFragment= ArtifactCardDetailFragment.newInstance(newId, title, imageUrl)
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, artifactCardDetailFragment!!, "artifactCardDetailFragment")
                .commit()
        }
    }
}