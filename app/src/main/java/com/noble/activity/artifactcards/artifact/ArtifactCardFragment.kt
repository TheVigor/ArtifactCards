package com.noble.activity.artifactcards.artifact

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.*
import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.utils.HERO_CARD_TYPE
import com.noble.activity.artifactcards.utils.showToast
import com.ruzhan.lion.listener.OnItemClickListener
import com.ruzhan.lion.model.LoadStatus
import kotlinx.android.synthetic.main.artifact_frag_card.*
import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import android.app.ProgressDialog
import android.view.WindowManager


class ArtifactCardFragment : Fragment(), OnFragmentLoadListener {

    companion object {

        private const val CARD_TYPE = "CARD_TYPE"

        @JvmStatic
        fun newInstance(type: String): ArtifactCardFragment {
            val args = Bundle()
            args.putString(CARD_TYPE, type)
            val frag = ArtifactCardFragment()
            frag.arguments = args
            return frag
        }
    }

    private var cardType: String = HERO_CARD_TYPE

    private lateinit var artifactCardViewModel: ArtifactCardViewModel
    private lateinit var artifactCardAdapter: ArtifactCardAdapter

    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.artifact_frag_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { cardType = it.getString(CARD_TYPE) }

        initRecyclerView()

        artifactCardViewModel = ViewModelProviders.of(this).get(ArtifactCardViewModel::class.java)
        initLiveData()

        artifactCardViewModel.loadLocalDbArtifactCards(cardType)

        if (refreshPrefs.isRefreshNeeded() && cardType == HERO_CARD_TYPE) {
            artifactCardViewModel.getAllCards(cardType)
        }
        //artifactCardViewModel.getArtifactCardsList(RequestStatus.REFRESH, cardType)


    }

    private fun initRecyclerView() {
        artifactCardAdapter =
                ArtifactCardAdapter(object : OnItemClickListener<Card> {
                    override fun onItemClick(position: Int, bean: Card, itemView: View) {
                        activity?.let {
                            //val url = if (bean.cover_url == null) "" else bean.cover_url.ori
                            ArtifactCardDetailActivity.launch(it,
                                bean.cardId.toString(),
                                bean.cardId.toString(),
                                bean.cardId.toString())
                        }
                    }
                })

        recycler_view.adapter = artifactCardAdapter

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                return artifactCardAdapter.getSpanSize(position)
            }
        }
        recycler_view.layoutManager = manager

    }

    private fun initLiveData() {

        progressDialog = ProgressDialog(activity, R.style.DownloadDialog)
        progressDialog.max = 100
        progressDialog.setMessage("Loading...")
        progressDialog.setTitle("Downloading cards database from official API")
        progressDialog.setCancelable(false)

        artifactCardViewModel.loadStatusLiveData.observe(this@ArtifactCardFragment,
            Observer { loadStatus ->
                loadStatus?.let {

                    if (LoadStatus.LOADING == loadStatus) {
                        loadingProgressBar.visibility = View.VISIBLE
                        progressDialog?.show()
                    } else {
                        progressDialog?.dismiss()
                        loadingProgressBar.visibility = View.GONE
                    }

                }
            })

        artifactCardViewModel.requestStatusLiveData.observe(this@ArtifactCardFragment,
            Observer { requestStatus ->
                requestStatus?.let {
                     artifactCardAdapter.setRefreshData(requestStatus.data)
                }
            })
    }

    override fun startLoadData() {
        artifactCardViewModel.loadLocalDbArtifactCards(cardType)
        if (refreshPrefs.isRefreshNeeded() && cardType == HERO_CARD_TYPE) {
            artifactCardViewModel.getAllCards(cardType)
        }
    }
}
