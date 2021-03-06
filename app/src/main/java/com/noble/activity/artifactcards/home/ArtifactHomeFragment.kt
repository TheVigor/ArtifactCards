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
import android.widget.CompoundButton
import android.widget.ImageView
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.colorData
import com.noble.activity.artifactcards.app.colorFilter
import com.noble.activity.artifactcards.app.searchData
import com.noble.activity.artifactcards.utils.*
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
        setupCheckBoxes()
        setupSearchView()

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
                search_view?.setQuery("", false)
                handler.removeCallbacks(fragmentLoadTask)
                onFragmentLoadListener = artifactHomeAdapter.getOnFragmentLoadListener(position)
                handler.postDelayed(fragmentLoadTask, 5)
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

    private fun setupCheckBoxes() {
        check_red.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                if (COLOR_RED !in colorFilter) {
                    colorFilter.add(COLOR_RED)
                }
                colorData.value = COLOR_RED
            } else {
                colorFilter.remove(COLOR_RED)
                colorData.value = NOT + COLOR_RED
            }
        }

        check_green.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                if (COLOR_GREEN !in colorFilter) {
                    colorFilter.add(COLOR_GREEN)
                }
                colorData.value = COLOR_GREEN
            } else {
                colorFilter.remove(COLOR_GREEN)
                colorData.value = NOT + COLOR_GREEN
            }
        }

        check_blue.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                if (COLOR_BLUE !in colorFilter) {
                    colorFilter.add(COLOR_BLUE)
                }
                colorData.value = COLOR_BLUE
            } else {
                colorFilter.remove(COLOR_BLUE)
                colorData.value = NOT + COLOR_BLUE
            }
        }

        check_black.setOnCheckedChangeListener{ _: CompoundButton, isChecked: Boolean ->
            if (isChecked) {
                if (COLOR_BLACK !in colorFilter) {
                    colorFilter.add(COLOR_BLACK)
                }
                colorData.value = COLOR_BLACK
            } else {
                colorFilter.remove(COLOR_BLACK)
                colorData.value = NOT + COLOR_BLACK
            }
        }
    }
    
    private fun setupSearchView() {
        search_view.queryHint = getString(R.string.card_name)
        search_view.maxWidth = Integer.MAX_VALUE

        search_view.setOnSearchClickListener {
            card_name.visibility = View.GONE

            check_red.visibility = View.GONE
            check_green.visibility = View.GONE
            check_blue.visibility = View.GONE
            check_black.visibility = View.GONE
        }

        search_view.setOnCloseListener {
            card_name.visibility = View.VISIBLE

            check_red.visibility = View.VISIBLE
            check_green.visibility = View.VISIBLE
            check_blue.visibility = View.VISIBLE
            check_black.visibility = View.VISIBLE

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
    }

    fun isSearchViewClosed(): Boolean {
        if (!search_view.isIconified) {
            search_view.isIconified = true
            return false
        }
        return true
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