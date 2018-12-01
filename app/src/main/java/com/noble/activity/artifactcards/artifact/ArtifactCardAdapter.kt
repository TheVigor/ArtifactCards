package com.noble.activity.artifactcards.artifact

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noble.activity.artifactcards.utils.PAGE_SIZE
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.listener.OnItemClickListener
import com.ruzhan.lion.ui.LoadMoreHolder
import java.util.ArrayList

class ArtifactCardAdapter(private var listener: OnItemClickListener<Card>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

        private const val LOAD_MORE = "LOAD_MORE"

        private const val TYPE_NORMAL = 1000
        private const val TYPE_LOAD_MORE = 1001
    }

    private val dataList = ArrayList<Any>()
    private var isLoadMore: Boolean = false

    fun setRefreshData(list: List<Card>) {
        if (list.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(list)
            dataList.add(LOAD_MORE)
            isLoadMore = list.size >= PAGE_SIZE
            notifyDataSetChanged()
        }
    }

    fun setLoadMoreData(list: List<Card>) {
        if (list.isNotEmpty()) {
            dataList.remove(LOAD_MORE)

            dataList.addAll(list)
            dataList.add(LOAD_MORE)
            isLoadMore = list.size >= PAGE_SIZE
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val obj = dataList[position]
        return if (obj is String) {
            TYPE_LOAD_MORE
        } else TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            TYPE_NORMAL -> viewHolder =
                    ArtifactCardHolder(
                        LayoutInflater.from(parent.context)
                            .inflate(
                                R.layout.artifact_item_new_list_grid,
                                parent,
                                false
                            ), listener
                    )

            TYPE_LOAD_MORE -> viewHolder = LoadMoreHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.artifact_item_load_more, parent, false))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_NORMAL -> (holder as ArtifactCardHolder).bind(dataList[position] as Card)
            TYPE_LOAD_MORE -> (holder as LoadMoreHolder).bind(isLoadMore)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getSpanSize(position: Int): Int {
        var spanSize = 1
        when (getItemViewType(position)) {
            TYPE_NORMAL -> spanSize = 1
            TYPE_LOAD_MORE -> spanSize = 2
        }
        return spanSize
    }
}
