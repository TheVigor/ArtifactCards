package com.noble.activity.artifactcards.artifact.detail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.databinding.ArtifactFragCardDetailBinding
import com.noble.activity.artifactcards.utils.InjectorUtils
import kotlinx.android.synthetic.main.artifact_frag_card_detail.*

class ArtifactCardDetailFragment : Fragment() {

    companion object {
        private const val NEW_ID = "newId"

        private const val NEW_FIRST_REF = "firstRef"
        private const val NEW_SECOND_REF = "secondRef"
        private const val NEW_THIRD_REF = "thirdRef"

        @JvmStatic
        fun newInstance(newId: String,
                        firstRefId: String,
                        secondRefId: String,
                        thirdRefId: String): ArtifactCardDetailFragment {
            val args = Bundle()

            args.putString(NEW_ID, newId)
            args.putString(NEW_FIRST_REF, firstRefId)
            args.putString(NEW_SECOND_REF, secondRefId)
            args.putString(NEW_THIRD_REF, thirdRefId)

            val frag = ArtifactCardDetailFragment()
            frag.arguments = args
            return frag
        }
    }

    private lateinit var newId: String

    private lateinit var firstRefId: String
    private lateinit var secondRefId: String
    private lateinit var thirdRefId: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        arguments?.let {
            newId = it.getString(NEW_ID)

            firstRefId = it.getString(NEW_FIRST_REF)
            secondRefId = it.getString(NEW_SECOND_REF)
            thirdRefId = it.getString(NEW_THIRD_REF)
        }

        val factory = InjectorUtils.provideArtifactCardDetailViewModelFactory(newId, firstRefId, secondRefId, thirdRefId)
        val artifactCardDetailViewModel = ViewModelProviders.of(this, factory)
            .get(ArtifactCardDetailViewModel::class.java)


        val binding = DataBindingUtil.inflate<ArtifactFragCardDetailBinding>(
            inflater, R.layout.artifact_frag_card_detail, container, false).apply {
            viewModel = artifactCardDetailViewModel
            setLifecycleOwner(this@ArtifactCardDetailFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(detail_toolbar)
    }

    private fun setToolbar(toolbar: Toolbar?) {
        if (toolbar == null) {
            return
        }
        val activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener { activity.onBackPressed() })

        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
}
