package com.noble.activity.artifactcards.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.noble.activity.artifactcards.utils.OnFragmentLoadListener
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.searchData
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

        setupToolbar(toolbar)

        search_view.queryHint = getString(R.string.card_name)
        search_view.maxWidth = Integer.MAX_VALUE

        search_view.setOnSearchClickListener {
            card_name.visibility = View.GONE
        }

        search_view.setOnCloseListener {
            card_name.visibility = View.VISIBLE
            false
        }

        val searchButton = search_view.findViewById<ImageView>(android.support.v7.appcompat.R.id.search_button)
        searchButton.setColorFilter(R.color.artifact_text_color)

        val closeButton = search_view.findViewById<ImageView>(android.support.v7.appcompat.R.id.search_close_btn)
        closeButton.setColorFilter(R.color.artifact_text_color)

        val searchAutoComplete = search_view.
            findViewById<SearchView.SearchAutoComplete>(android.support.v7.appcompat.R.id.search_src_text)
        searchAutoComplete.setHintTextColor(resources.getColor(R.color.artifact_white_f5))
        searchAutoComplete.setTextColor(resources.getColor(R.color.artifact_white_f5))

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchData.value = query
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                searchData.value = query
                return false
            }
        })


        val titleList = getTitleList()

        artifactHomeAdapter = ArtifactHomeAdapter(childFragmentManager, titleList)
        view_pager.adapter = artifactHomeAdapter


        tabs.tabMode = android.support.design.widget.TabLayout.MODE_SCROLLABLE
        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)?.setIcon(R.drawable.ic_hero)
        tabs.getTabAt(1)?.setIcon(R.drawable.ic_spell)
        tabs.getTabAt(2)?.setIcon(R.drawable.ic_item)
        tabs.getTabAt(3)?.setIcon(R.drawable.ic_improvement)
        tabs.getTabAt(4)?.setIcon(R.drawable.ic_creep)

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

    private fun setupToolbar(toolbar: Toolbar) {
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