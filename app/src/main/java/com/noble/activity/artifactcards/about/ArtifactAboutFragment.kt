package com.noble.activity.artifactcards.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import com.vansuita.materialabout.builder.AboutBuilder
import com.vansuita.materialabout.views.AboutView
import kotlinx.android.synthetic.main.artifact_frag_about.*

class ArtifactAboutFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ArtifactAboutFragment()

        private const val DEV_EMAIL = "the.noble.activity@gmail.com"
        private const val DEV_GITHUB = "TheVigor"
        private const val DEV_COMPANY_NAME = "Noble Activity"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.artifact_frag_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAndAddAboutView()
    }

    private fun createAndAddAboutView() {
        val aboutBuilder: AboutBuilder = AboutBuilder.with(activity)
            .setAppIcon(R.mipmap.ic_launcher_round)
            .setAppName(R.string.app_name)
            .setPhoto(R.mipmap.ic_launcher_round)
            .setCover(R.mipmap.profile_cover)
            .setLinksAnimated(true)
            .setDividerDashGap(13)
            .setName(R.string.app_name)
            .setSubTitle(R.string.sub_title)
            .setBrief(R.string.brief)
            .setLinksColumnsCount(2)
            .addGitHubLink(DEV_GITHUB)
            .addEmailLink(DEV_EMAIL)
            .addFiveStarsAction()
            .addMoreFromMeAction(DEV_COMPANY_NAME)
            .setVersionNameAsAppSubTitle()
            .addShareAction(R.string.app_name)
            .setActionsColumnsCount(2)
            .addFeedbackAction(DEV_EMAIL)
            .setWrapScrollView(true)
            .setShowAsCard(true)

        val aboutView: AboutView = aboutBuilder.build()
        constraint_layout.addView(aboutView)
    }
}