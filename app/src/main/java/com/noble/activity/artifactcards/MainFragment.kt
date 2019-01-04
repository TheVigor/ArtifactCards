package com.noble.activity.artifactcards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.about.ArtifactAboutFragment
import com.noble.activity.artifactcards.home.ArtifactHomeFragment
import com.noble.activity.artifactcards.settings.ArtifactSettingsFragment
import kotlinx.android.synthetic.main.frag_main.*

class MainFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private val fragmentMap = HashMap<String, Fragment>()

    var artifactHomeFragment: ArtifactHomeFragment? = null
    var artifactSettingsFragment: ArtifactSettingsFragment? = null
    var artifactAboutFragment: ArtifactAboutFragment? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottom_navigation.setTextVisibility(false)
        bottom_navigation.enableAnimation(false)

        bottom_navigation.setOnNavigationItemSelectedListener {
            if (bottom_navigation.selectedItemId != it.itemId) {
                replaceFragment(it.itemId)
            }
            true
        }

        replaceFragment(R.id.artifact_home)
    }

    private fun replaceFragment(tabId: Int) {
        val fm = childFragmentManager
        val transaction = fm.beginTransaction()

        for (frag in fragmentMap.values) {
            transaction.hide(frag)
        }

        var fragTag: String? = null
        var frag: Fragment? = null

        when (tabId) {
            R.id.artifact_home -> {
                fragTag = "ArtifactHomeFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArtifactHomeFragment.newInstance()
                    artifactHomeFragment = frag
                    transaction.add(R.id.container, frag, fragTag)

                } else {
                    transaction.show(frag)
                }
            }

            R.id.artifact_settings -> {
                fragTag = "ArtifactSettingsFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArtifactSettingsFragment.newInstance()
                    artifactSettingsFragment = frag
                    transaction.add(R.id.container, frag, fragTag)
                } else {
                    transaction.show(frag)
                }
            }

            R.id.artifact_about -> {
                fragTag = "ArtifactAboutFragment"
                frag = fragmentMap[fragTag]

                if (frag == null) {
                    frag = ArtifactAboutFragment.newInstance()
                    artifactAboutFragment = frag
                    transaction.add(R.id.container, frag, fragTag)
                } else {
                    transaction.show(frag)
                }
            }

        }
        if (fragTag != null && frag != null) {
            fragmentMap[fragTag] = frag
        }

        if (!fm.isDestroyed) {
            transaction.commitAllowingStateLoss()
        }
    }
}