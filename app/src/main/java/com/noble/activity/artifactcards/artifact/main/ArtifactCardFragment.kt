package com.noble.activity.artifactcards.artifact.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.*
import com.noble.activity.artifactcards.model.card.Card
import kotlinx.android.synthetic.main.artifact_frag_card.*
import android.app.ProgressDialog
import com.noble.activity.artifactcards.app.colorData
import com.noble.activity.artifactcards.app.refreshPrefs
import com.noble.activity.artifactcards.app.searchData
import com.noble.activity.artifactcards.artifact.detail.ArtifactCardDetailActivity
import com.noble.activity.artifactcards.utils.*

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

        arguments?.let {
            cardType = it.getString(CARD_TYPE)
        }


        initRecyclerView()

        artifactCardViewModel = ViewModelProviders.of(this).get(ArtifactCardViewModel::class.java)
        initLiveData()

        if (refreshPrefs.isRefreshNeeded() && cardType == HERO_CARD_TYPE) {
            artifactCardViewModel.getAllCards(cardType)
        }

        artifactCardViewModel.loadLocalDbArtifactCards(cardType)
    }

    private fun initRecyclerView() {
        artifactCardAdapter =
                ArtifactCardAdapter(object :
                    OnItemClickListener<Card> {
                    override fun onItemClick(position: Int, bean: Card, itemView: View) {
                        activity?.let {
                            var firstRefId = 0
                            var secondRefId = 0
                            var thirdRefId = 0

                            if (bean.references.size >= 3) {
                                firstRefId = bean.references[0].cardId
                                secondRefId = bean.references[1].cardId
                                thirdRefId = bean.references[2].cardId
                            }

                            if (bean.references.size == 2) {
                                firstRefId = bean.references[0].cardId
                                secondRefId = bean.references[1].cardId
                            }

                            if (bean.references.size == 1) {
                                firstRefId = bean.references[0].cardId
                            }

                            ArtifactCardDetailActivity.launch(
                                it,
                                bean.cardId.toString(),
                                bean.cardName.english!!,
                                firstRefId.toString(),
                                secondRefId.toString(),
                                thirdRefId.toString()
                            )
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

        searchData.observe(this@ArtifactCardFragment,
            Observer { query ->
                query?.let {
                    artifactCardAdapter.filter.filter(query)
                }
            })

        colorData.observe(this@ArtifactCardFragment,
            Observer { color ->
                color?.let {
                    artifactCardAdapter.filter.filter(searchData.value ?: "")
                }
            })


        progressDialog = ProgressDialog(activity, R.style.DownloadDialog)
        progressDialog.max = 100
        progressDialog.setMessage(getString(R.string.loading))
        progressDialog.setTitle(getString(R.string.downloading_from_api))
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
        if (refreshPrefs.isRefreshNeeded() && cardType == HERO_CARD_TYPE) {
            artifactCardViewModel.getAllCards(cardType)
        }
        artifactCardViewModel.loadLocalDbArtifactCards(cardType)
    }
}
