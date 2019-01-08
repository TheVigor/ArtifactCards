package com.noble.activity.artifactcards.deck

import android.app.Activity
import android.content.Context
import android.support.v4.os.ConfigurationCompat
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckDetailItemBinding
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckDetailAdapter : ListAdapter<Card,
        ArtifactDeckDetailAdapter.ViewHolder>(ArtifactDeckDetailDiffCallback()) {

    private val locale: String = ConfigurationCompat.getLocales(app.resources.configuration)[0].language

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.apply {
            bind(createOnClickListener(card, holder.itemView.context), card, locale)
            //itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArtifactFragDeckDetailItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(cardDeck: Card, context: Context): View.OnClickListener {
        return View.OnClickListener {
            //ArtifactDeckDetailActivity.launch(context as Activity, cardDeck)
        }
    }

    class ViewHolder(
        private val binding: ArtifactFragDeckDetailItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Card, locale: String) {
            binding.apply {
                clickListener = listener
                card = item

                //cardViewDeck.setCardBackgroundColor(item.getTextColor())

                cardMana.setTextColor(item.getColorByCardType())
                cardName.text = item.getNameByLocale(locale)
                cardCount.text = "x" + App.cardDeck.mapCountTurn[item.cardId].toString()
                executePendingBindings()
            }
        }
    }
}