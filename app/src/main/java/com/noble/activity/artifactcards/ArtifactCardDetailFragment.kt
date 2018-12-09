package com.noble.activity.artifactcards

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.databinding.ArtifactFragCardDetailBinding
import com.noble.activity.artifactcards.utils.InjectorUtils
import com.noble.activity.artifactcards.viewmodels.ArtifactCardDetailViewModel
import kotlinx.android.synthetic.main.artifact_frag_card_detail.*


class ArtifactCardDetailFragment : Fragment() {

    companion object {

        private const val NEW_ID = "newId"

        private const val NEW_FIRST_REF = "firstRef"
        private const val NEW_SECOND_REF = "secondRef"
        private const val NEW_THIRD_REF = "thirdRef"

        private const val NEW_URL = "newUrl"

        private const val RESET_EDIT_VALUE = 30

        @JvmStatic
        fun newInstance(newId: String,
                        firstRefId: String,
                        secondRefId: String,
                        thirdRefId: String,
                        imageUrl: String): ArtifactCardDetailFragment {
            val args = Bundle()
            args.putString(NEW_ID, newId)
            args.putString(NEW_FIRST_REF, firstRefId)
            args.putString(NEW_SECOND_REF, secondRefId)
            args.putString(NEW_THIRD_REF, thirdRefId)

            args.putString(NEW_URL, imageUrl)
            val frag = ArtifactCardDetailFragment()
            frag.arguments = args
            return frag
        }
    }

    private lateinit var newId: String

    private lateinit var firstRefId: String
    private lateinit var secondRefId: String
    private lateinit var thirdRefId: String

    private lateinit var imageUrl: String

    //private val header: Header = Header()

    //private lateinit var articleNewDetailViewModel: ArticleNewDetailViewModel
    //private lateinit var articleNewDetailAdapter: ArticleNewDetailAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        arguments?.let {
            newId = it.getString(NEW_ID)
            firstRefId = it.getString(NEW_FIRST_REF)
            secondRefId = it.getString(NEW_SECOND_REF)
            thirdRefId = it.getString(NEW_THIRD_REF)
            imageUrl = it.getString(NEW_URL)
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

        detail_toolbar.title = firstRefId
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

    private fun initRecyclerView() {
//        articleNewDetailAdapter = ArticleNewDetailAdapter(
//            object : OnItemClickListener<NewEle> { // image
//
//                override fun onItemClick(position: Int, bean: NewEle, itemView: View) {
//                    val imageUrlList = articleNewDetailAdapter.getImageUrlList()
//                    val imageUrl = bean.imgUrl
//                    val imageListModel = ImageListModel(firstRefId, imageUrlList.indexOf(imageUrl),
//                        imageUrl, imageUrlList)
//
//                    activity?.let {
//                        ImageDetailActivity.launch(it, imageListModel)
//                    }
//                }
//            },
//            object : OnItemClickListener<NewEle> { // video
//
//                override fun onItemClick(position: Int, bean: NewEle, itemView: View) {
//                    Flowable.create<String>({ e ->
//                        val playUrl = HtmlParser.htmlToVideoUrl(bean.html)
//                        e.onNext(playUrl)
//                        e.onComplete()
//
//                    }, BackpressureStrategy.LATEST)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .doOnError(Throwable::printStackTrace)
//                        .doOnNext { url ->
//                            activity?.let {
//                                WebVideoActivity.launch(it, url)
//                            }
//                        }
//                        .doOnComplete { }
//                        .subscribe(Subscriber.create())
//                }
//            },
//            object : OnItemClickListener<String> { // comment more
//
//                override fun onItemClick(position: Int, bean: String, itemView: View) {
//                    activity?.let {
//                        ArticleCommentListActivity.launch(it, newId)
//                    }
//                }
//            })
//
//        articleNewDetailAdapter.setHeaderData(header)
//        recycler_view.adapter = articleNewDetailAdapter
//
//        OnRefreshHelper.setOnRefreshStatusListener(swipe_refresh, recycler_view, object :
//            OnRefreshHelper.OnRefreshStatusListener {
//
//            override fun onRefresh() {
//                articleNewDetailViewModel.getNewDetail(RequestStatus.REFRESH, newId)
//            }
//
//            override fun onLoadMore() {
//
//            }
//        })
    }

    private fun initLiveData() {
//        articleNewDetailViewModel.loadStatusLiveData.observe(this@ArticleNewDetailFragment,
//            Observer { loadStatus ->
//                loadStatus?.let {
//                    swipe_refresh.isRefreshing = LoadStatus.LOADING == loadStatus
//                }
//            })
//
//        articleNewDetailViewModel.requestStatusLiveData.observe(this@ArticleNewDetailFragment,
//            Observer { requestStatus ->
//                requestStatus?.let {
//                    when (requestStatus.refreshStatus) {
//                        RequestStatus.REFRESH ->
//                            newDetailToNewEleList(requestStatus.data)
//                    }
//                }
//            })
//
//        articleNewDetailViewModel.commentsLiveData.observe(this@ArticleNewDetailFragment,
//            Observer { commentList ->
//                commentList?.let {
//                    articleNewDetailAdapter.setCommentListData(it)
//                }
//            })
//
//        articleNewDetailViewModel.newElesLiveData.observe(this@ArticleNewDetailFragment,
//            Observer { newEleList ->
//                newEleList?.let {
//                    articleNewDetailAdapter.setNewEleData(it)
//                }
//            })
//
//        articleNewDetailViewModel.sendCommentLiveData.observe(this@ArticleNewDetailFragment,
//            Observer { comment ->
//                comment?.let {
//                    val str = resources.getString(R.string.awaker_article_comment_suc)
//                    Toast.makeText(context, str + "", Toast.LENGTH_LONG).show()
//                    comment_et.setText("")
//                    comment_et.isFocusable = false
//                    comment_et.isFocusableInTouchMode = true
//
//                    activity?.let { KeyboardUtils.closeSoftInput(it, comment_et) }
//                    articleNewDetailViewModel.getHotCommentList(newId)
//                }
//            })
    }

//    private fun initListener() {
//        send_iv.setOnClickListener(View.OnClickListener {
//            val content = comment_et.text.toString().trim()
//            if (TextUtils.isEmpty(content)) {
//                return@OnClickListener
//            }
//            //articleNewDetailViewModel.sendNewsComment(newId, content, "", "")
//        })
//
////        recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
////            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
////                super.onScrolled(recyclerView!!, dx, dy)
////                if (Math.abs(dy) > RESET_EDIT_VALUE && comment_et.isFocusable) {
////                    comment_et.setText("")
////                    comment_et.isFocusable = false
////                    comment_et.isFocusableInTouchMode = true
////                    //activity?.let { KeyboardUtils.closeSoftInput(it, comment_et) }
////                }
////            }
////        })
//
//        comment_et.setOnClickListener {
//            comment_et.isFocusable = true
//            comment_et.requestFocus()
//        }
//
//        comment_right_tv.setOnClickListener {
//            //activity?.let { ArticleCommentListActivity.launch(it, newId) }
//        }
//
//        comment_et.setOnFocusChangeListener { _, hasFocus ->
//            send_iv.visibility = if (hasFocus) View.VISIBLE else View.GONE
//            comment_right_tv.visibility = if (hasFocus) View.GONE else View.VISIBLE
//        }
//    }

//    private fun newDetailToNewEleList(newDetail: NewDetail) {
//        val user = newDetail.user
//        user?.let {
//            header.userName = user.nickname
//            header.userUrl = user.avatar128
//        }
//
//        header.firstRefId = newDetail.firstRefId
//
//        newDetail.cover_url?.let {
//            header.url = newDetail.cover_url.ori
//        }
//
//        header.createTime = newDetail.create_time
//        articleNewDetailAdapter.setHeader(header)
//
//        newDetail.comment?.let {
//            val commentCountStr = ResUtils.getString(R.string.awaker_article_new_comment_count,
//                newDetail.comment)
//            comment_right_tv.text = commentCountStr
//        }
//
//        val html = newDetail.content
//        html?.let {
//            articleNewDetailViewModel.articleHtmlToModelList(html)
//        }
//    }
}
