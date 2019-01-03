package com.noble.activity.artifactcards

import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.noble.activity.artifactcards.artifact.main.ArtifactCardViewModel
import com.noble.activity.artifactcards.utils.HERO_CARD_TYPE
import com.noble.activity.artifactcards.utils.LoadStatus
import com.noble.activity.artifactcards.utils.showToast

class MainActivity : AppCompatActivity() {

    private var mainFragment: MainFragment? = null

    private lateinit var updateViewModel: ArtifactCardViewModel
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.container)

        updateViewModel = ViewModelProviders.of(this).get(ArtifactCardViewModel::class.java)
        initLiveData()

        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, mainFragment!!, "MainFragment")
                .commit()
        }
    }

    override fun onBackPressed() {
        var back = mainFragment?.artifactHomeFragment?.isSearchViewClosed()
        if (back != null) {
            if (!back) return
        }

        super.onBackPressed()
    }

    fun updateCards(view: View) {
        updateViewModel.getAllCards(HERO_CARD_TYPE)
    }

    private fun initLiveData() {
        progressDialog = ProgressDialog(this, R.style.DownloadDialog)
        progressDialog.max = 100
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.setTitle(getString(R.string.downloading_from_api))
        progressDialog.setCancelable(false)

        updateViewModel.loadStatusLiveData.observe(this@MainActivity,
            Observer { loadStatus ->
                loadStatus?.let {
                    if (LoadStatus.LOADING == loadStatus) {
                        progressDialog?.show()
                    } else {
                        progressDialog?.dismiss()
                    }
                }
            })
    }
}
