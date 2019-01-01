package com.noble.activity.artifactcards.artifact.main

import android.support.v4.os.ConfigurationCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.*
import java.util.ArrayList

class ArtifactCardAdapter(private var listener: OnItemClickListener<Card>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    private val locale: String = ConfigurationCompat.getLocales(app.resources.configuration)[0].language

    companion object {
        private const val TYPE_HERO = 1000
        private const val TYPE_SPELL = 1001
        private const val TYPE_ITEM = 1002
        private const val TYPE_IMPROVEMENT = 1003
        private const val TYPE_CREEP = 1004
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

    override fun getItemViewType(position: Int) =  when(cardListFiltered[position].cardType) {
        HERO_CARD_TYPE -> TYPE_HERO
        SPELL_CARD_TYPE -> TYPE_SPELL
        ITEM_CARD_TYPE -> TYPE_ITEM
        IMPROVEMENT_CARD_TYPE -> TYPE_IMPROVEMENT
        CREEP_CARD_TYPE -> TYPE_CREEP
        else -> TYPE_HERO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        lateinit var viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            TYPE_HERO -> viewHolder = ArtifactCardHeroHolder(LayoutInflater.from(parent.context).inflate(R.layout.artifact_item_hero, parent, false), listener)
            TYPE_SPELL -> viewHolder = ArtifactCardSpellHolder(LayoutInflater.from(parent.context).inflate(R.layout.artifact_item_spell, parent, false), listener)
            TYPE_ITEM -> viewHolder = ArtifactCardItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.artifact_item_item, parent, false), listener)
            TYPE_IMPROVEMENT -> viewHolder = ArtifactCardSpellHolder(LayoutInflater.from(parent.context).inflate(R.layout.artifact_item_spell, parent, false), listener)
            TYPE_CREEP -> viewHolder = ArtifactCardCreepHolder(LayoutInflater.from(parent.context).inflate(R.layout.artifact_item_creep, parent, false), listener)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_HERO -> (holder as ArtifactCardHeroHolder).bind(cardListFiltered[position], locale)
            TYPE_SPELL -> (holder as ArtifactCardSpellHolder).bind(cardListFiltered[position], locale)
            TYPE_ITEM -> (holder as ArtifactCardItemHolder).bind(cardListFiltered[position], locale)
            TYPE_IMPROVEMENT -> (holder as ArtifactCardSpellHolder).bind(cardListFiltered[position], locale)
            TYPE_CREEP -> (holder as ArtifactCardCreepHolder).bind(cardListFiltered[position], locale)
        }
    }

    override fun getItemCount(): Int {
        return cardListFiltered.size
    }

    fun getSpanSize(position: Int): Int {
        return 1
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
