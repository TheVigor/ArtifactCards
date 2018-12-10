package com.noble.activity.artifactcards.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.utils.OnFragmentLoadListener
import com.noble.activity.artifactcards.R
import kotlinx.android.synthetic.main.artifact_frag_home.*

class ArtifactHomeFragment : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() = ArtifactHomeFragment()
    }

    private lateinit var artifactHomeAdapter: ArtifactHomeAdapter

    private var onFragmentLoadListener: OnFragmentLoadListener? = null

    private val fragmentLoadTask: FragmentLoadTask = FragmentLoadTask()
    private val handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.artifact_frag_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        card_name.text = resources.getString(R.string.app_name)
        setToolbar(toolbar)

        val titleList = getTitleList()

        artifactHomeAdapter = ArtifactHomeAdapter(childFragmentManager, titleList)
        view_pager.adapter = artifactHomeAdapter

        tabs.tabMode = android.support.design.widget.TabLayout.MODE_SCROLLABLE
        tabs.setupWithViewPager(view_pager)

        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                handler.removeCallbacks(fragmentLoadTask)
                onFragmentLoadListener = artifactHomeAdapter.getOnFragmentLoadListener(position)
                handler.postDelayed(fragmentLoadTask, 600)
            }
        })
    }

    private fun getTitleList() = ArrayList<String>().apply {
        add(getString(R.string.heroes))
        add(getString(R.string.spells))
        add(getString(R.string.items))
        add(getString(R.string.improvements))
        add(getString(R.string.creeps))
    }

    private fun setToolbar(toolbar: Toolbar) {
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar)
    }

    inner class FragmentLoadTask : Runnable {
        override fun run() {
            onFragmentLoadListener?.startLoadData()
        }
    }

    override fun onDestroy() {
        handler.removeCallbacks(fragmentLoadTask)
        super.onDestroy()
    }
}