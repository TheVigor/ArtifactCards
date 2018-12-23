package com.noble.activity.artifactcards.artifact.main

import android.support.v4.os.ConfigurationCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.app.colorFilter
import com.noble.activity.artifactcards.utils.OnItemClickListener
import com.noble.activity.artifactcards.model.Card
import java.util.ArrayList

class ArtifactCardAdapter(private var listener: OnItemClickListener<Card>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val locale: String = ConfigurationCompat.getLocales(app.resources.configuration)[0].language

    companion object {
        private const val TYPE_NORMAL = 1000
    }

    private val cardList = ArrayList<Card>()
    private var cardListFiltered = ArrayList<Card>()

    fun setRefreshData(list: List<Card>) {
        if (list.isNotEmpty()) {
            cardList.clear()
            cardList.addAll(list)

            cardListFiltered.clear()
            cardListFiltered.addAll(list.filter { it.isMatchColor() })

            notifyDataSetChanged()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_NORMAL
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
            TYPE_NORMAL -> (holder as ArtifactCardHolder).bind(cardListFiltered[position], locale)
        }
    }

    override fun getItemCount(): Int {
        return cardListFiltered.size
    }

    fun getSpanSize(position: Int): Int {
        var spanSize = 1
        when (getItemViewType(position)) {
            TYPE_NORMAL -> spanSize = 1
        }
        return spanSize
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val query = charSequence.toString()
                if (query.isEmpty()) {
                    cardListFiltered = cardList
                } else {
                    val filteredList = ArrayList<Card>()
                    for (card in cardList) {
                        if (card.isMatchQuery(query)) {
                            filteredList.add(card)
                        }
                    }
                    cardListFiltered = filteredList
                }

                cardListFiltered =
                        ArrayList(cardListFiltered.filter { it.isMatchColor() })

                val filterResults = Filter.FilterResults()
                filterResults.values = cardListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                cardListFiltered = filterResults.values as ArrayList<Card>
                notifyDataSetChanged()
            }
        }
    }

}
