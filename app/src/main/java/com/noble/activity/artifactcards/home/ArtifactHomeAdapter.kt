package com.noble.activity.artifactcards.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.noble.activity.artifactcards.OnFragmentLoadListener
import com.noble.activity.artifactcards.artifact.ArtifactCardFragment
import java.lang.ref.WeakReference

class ArtifactHomeAdapter(fm: FragmentManager, private val titleList: List<String>)
    : FragmentStatePagerAdapter(fm) {

    private val fragMap: HashMap<Int, WeakReference<OnFragmentLoadListener>> = HashMap()

    override fun getItem(position: Int): Fragment {
        lateinit var frag: Fragment
        when (position) {
            0 -> frag = ArtifactCardFragment.newInstance(0) //
            1 -> frag = ArtifactCardFragment.newInstance(1) // 每周热读
            2 -> frag = ArtifactCardFragment.newInstance(2) // 每周热评
            3 -> frag = ArtifactCardFragment.newInstance(3) // 灵性觉醒
            4 -> frag = ArtifactCardFragment.newInstance(4) // 科学探索
            5 -> frag = ArtifactCardFragment.newInstance(5) // UFO
            6 -> frag = ArtifactCardFragment.newInstance(6) // 自由能源
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