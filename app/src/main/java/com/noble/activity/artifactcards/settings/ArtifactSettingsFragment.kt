package com.noble.activity.artifactcards.settings

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import kotlinx.android.synthetic.main.artifact_frag_settings.*
import android.widget.ArrayAdapter
import com.noble.activity.artifactcards.utils.showToast
import kotlinx.android.synthetic.main.update_cards_db_button.*


class ArtifactSettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ArtifactSettingsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = super.onCreateView(inflater, container, savedInstanceState)
        val activity = activity as AppCompatActivity
        activity.setSupportActionBar(toolbar_settings)

        return root
    }

}