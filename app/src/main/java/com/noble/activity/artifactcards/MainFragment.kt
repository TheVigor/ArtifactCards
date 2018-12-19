package com.noble.activity.artifactcards

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.home.ArtifactHomeFragment

class MainFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }

    private val fragmentMap = HashMap<String, Fragment>()

    var artifactHomeFragment: ArtifactHomeFragment? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replaceFragment(R.id.artifact)
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
            R.id.artifact -> {
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
        }
        if (fragTag != null && frag != null) {
            fragmentMap[fragTag] = frag
        }

        if (!fm.isDestroyed) {
            transaction.commitAllowingStateLoss()
        }
    }
}