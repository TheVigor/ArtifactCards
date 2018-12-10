package com.noble.activity.artifactcards.artifact

import android.support.v4.os.ConfigurationCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.noble.activity.artifactcards.App
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.model.Card
import java.util.ArrayList

class ArtifactCardAdapter(private var listener: OnItemClickListener<Card>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val locale: String = ConfigurationCompat.getLocales(App.get()?.resources?.configuration)[0].language

    companion object {
        private const val TYPE_NORMAL = 1000
    }

    private val dataList = ArrayList<Any>()

    fun setRefreshData(list: List<Card>) {
        if (list.isNotEmpty()) {
            dataList.clear()
            dataList.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return  TYPE_NORMAL
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

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_NORMAL -> (holder as ArtifactCardHolder).bind(dataList[position] as Card, locale)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getSpanSize(position: Int): Int {
        var spanSize = 1
        when (getItemViewType(position)) {
            TYPE_NORMAL -> spanSize = 1
        }
        return spanSize
    }
}
