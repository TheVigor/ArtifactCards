package com.noble.activity.artifactcards.deck

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.deck.model.CardDeck

class ArtifactDeckDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun launch(activity: Activity, cardDeck: CardDeck) {
            val intent = Intent(activity, ArtifactDeckDetailActivity::class.java)

            App.cardDeck = cardDeck

            activity.startActivity(intent)
        }
    }

    private var artifactDeckDetailFragment: ArtifactDeckDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.container)

//        app.counterAd++
//        if (ad.isLoaded && (app.counterAd % AD_COUNTER == 0)) {
//            app.counterAd = 0
//            ad.show()
//        }


        if (artifactDeckDetailFragment == null) {
            artifactDeckDetailFragment =
                    ArtifactDeckDetailFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, artifactDeckDetailFragment!!, "artifactDeckDetailFragment")
                .commit()
        }
    }
}