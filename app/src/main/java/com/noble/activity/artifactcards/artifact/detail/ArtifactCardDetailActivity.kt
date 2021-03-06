package com.noble.activity.artifactcards.artifact.detail

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.noble.activity.artifactcards.R

class ArtifactCardDetailActivity : AppCompatActivity() {

    companion object {
        private const val NEW_ID = "newId"
        private const val NEW_NAME = "newName"

        private const val NEW_FIRST_REF = "firstRef"
        private const val NEW_SECOND_REF = "secondRef"
        private const val NEW_THIRD_REF = "thirdRef"

        @JvmStatic
        fun launch(activity: Activity, newId: String, newName: String, firstRefId: String, secondRefId: String, thirdRefId: String) {
            val intent = Intent(activity, ArtifactCardDetailActivity::class.java)

            intent.putExtra(NEW_ID, newId)
            intent.putExtra(NEW_NAME, newName)
            intent.putExtra(NEW_FIRST_REF, firstRefId)
            intent.putExtra(NEW_SECOND_REF, secondRefId)
            intent.putExtra(NEW_THIRD_REF, thirdRefId)

            activity.startActivity(intent)
        }
    }



    private lateinit var newId: String
    private lateinit var newName: String

    private lateinit var firstRefId: String
    private lateinit var secondRefId: String
    private lateinit var thirdRefId: String

    private var artifactCardDetailFragment: ArtifactCardDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.container)

        newId = intent.getStringExtra(NEW_ID)
        newName = intent.getStringExtra(NEW_NAME)

        firstRefId = intent.getStringExtra(NEW_FIRST_REF)
        secondRefId = intent.getStringExtra(NEW_SECOND_REF)
        thirdRefId = intent.getStringExtra(NEW_THIRD_REF)

        if (artifactCardDetailFragment == null) {
            artifactCardDetailFragment=
                    ArtifactCardDetailFragment.newInstance(
                        newId,
                        newName,
                        firstRefId,
                        secondRefId,
                        thirdRefId
                    )
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, artifactCardDetailFragment!!, "artifactCardDetailFragment")
                .commit()
        }
    }
}