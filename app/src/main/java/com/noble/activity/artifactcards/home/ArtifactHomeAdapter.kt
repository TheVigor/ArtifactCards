package com.noble.activity.artifactcards.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.noble.activity.artifactcards.utils.OnFragmentLoadListener
import com.noble.activity.artifactcards.artifact.main.ArtifactCardFragment
import com.noble.activity.artifactcards.utils.*
import java.lang.ref.WeakReference

class ArtifactHomeAdapter(fm: FragmentManager, private val titleList: List<String>)
    : FragmentStatePagerAdapter(fm) {

    private val fragMap: HashMap<Int, WeakReference<OnFragmentLoadListener>> = HashMap()

    override fun getItem(position: Int): Fragment {
        lateinit var frag: Fragment
        when (position) {
            0 -> frag = ArtifactCardFragment.newInstance(HERO_CARD_TYPE) // Heroes
            1 -> frag = ArtifactCardFragment.newInstance(SPELL_CARD_TYPE) // Spells
            2 -> frag = ArtifactCardFragment.newInstance(ITEM_CARD_TYPE) // Items
            3 -> frag = ArtifactCardFragment.newInstance(IMPROVEMENT_CARD_TYPE) // Improvements
            4 -> frag = ArtifactCardFragment.newInstance(CREEP_CARD_TYPE) // Creeps
        }
        if (frag is OnFragmentLoadListener) {
            fragMap[position] = WeakReference<OnFragmentLoadListener>(frag)
        }
        return frag
    }

    override fun getCount(): Int {
        return titleList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    fun getOnFragmentLoadListener(position: Int): OnFragmentLoadListener? {
        var listener: OnFragmentLoadListener? = null
        val weakRefValue = fragMap[position]
        weakRefValue?.let {
            val realValue = it.get()
            realValue?.let { listener = it }
        }
        return listener
    }
}