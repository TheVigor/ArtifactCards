package com.noble.activity.artifactcards.artifact

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.OnFragmentLoadListener
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.helper.OnRefreshHelper
import com.ruzhan.lion.listener.OnItemClickListener
import com.ruzhan.lion.model.LoadStatus
import com.ruzhan.lion.model.RequestStatus
import kotlinx.android.synthetic.main.artifact_frag_data_type.*

class ArtifactCardFragment : Fragment(), OnFragmentLoadListener {

    companion object {

        private const val NEW_ID = "NEW_ID"

        @JvmStatic
        fun newInstance(newId: Int): ArtifactCardFragment {
            val args = Bundle()
            args.putInt(NEW_ID, newId)
            val frag = ArtifactCardFragment()
            frag.arguments = args
            return frag
        }
    }

    private var newId: Int? = null

    private lateinit var otherArticleNewAllViewModel: ArtifactCardViewModel
    private lateinit var articleNewAllAdapter: ArtifactCardAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.artifact_frag_data_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { newId = it.getInt(NEW_ID) }

        initRecyclerView()

        otherArticleNewAllViewModel = ViewModelProviders.of(this).get(ArtifactCardViewModel::class.java)
        initLiveData()

        otherArticleNewAllViewModel.loadLocalOtherNews(newId.toString())
        otherArticleNewAllViewModel.getArtifactCardsList(RequestStatus.REFRESH, newId.toString())


    }

    private fun initRecyclerView() {
        articleNewAllAdapter =
                ArtifactCardAdapter(object : OnItemClickListener<Card> {
                    override fun onItemClick(position: Int, bean: Card, itemView: View) {
                        activity?.let {
                            //val url = if (bean.cover_url == null) "" else bean.cover_url.ori
                            //ArticleNewDetailActivity.launch(it, bean.id, bean.title, url)
                        }
                    }
                })

        recycler_view.adapter = articleNewAllAdapter

        val manager = GridLayoutManager(activity, 2)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int): Int {
                return articleNewAllAdapter.getSpanSize(position)
            }
        }
        recycler_view.layoutManager = manager

        OnRefreshHelper.setOnRefreshStatusListener(swipe_refresh, recycler_view, object :
            OnRefreshHelper.OnRefreshStatusListener {

            override fun onRefresh() {
                otherArticleNewAllViewModel.getArtifactCardsList(RequestStatus.REFRESH, newId.toString())
            }

            override fun onLoadMore() {
                //otherArticleNewAllViewModel.getArtifactCardsList(RequestStatus.LOAD_MORE, newId.toString())
            }
        })
    }

    private fun initLiveData() {
        otherArticleNewAllViewModel.loadStatusLiveData.observe(this@ArtifactCardFragment,
            Observer { loadStatus ->
                loadStatus?.let {
                    swipe_refresh.isRefreshing = LoadStatus.LOADING == loadStatus
                }
            })

        otherArticleNewAllViewModel.requestStatusLiveData.observe(this@ArtifactCardFragment,
            Observer { requestStatus ->
                requestStatus?.let {
                    when (requestStatus.refreshStatus) {
                        RequestStatus.REFRESH -> articleNewAllAdapter.setRefreshData(requestStatus.data)
                        RequestStatus.LOAD_MORE -> articleNewAllAdapter.setLoadMoreData(requestStatus.data)
                    }
                }
            })
    }

    override fun startLoadData() {
        otherArticleNewAllViewModel.loadLocalOtherNews(newId.toString())
        otherArticleNewAllViewModel.getArtifactCardsList(RequestStatus.REFRESH, newId.toString())
    }
}
