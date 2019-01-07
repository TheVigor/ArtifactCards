package com.noble.activity.artifactcards.deck

import android.app.Activity
import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.artifact.detail.ArtifactCardDetailActivity
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckItemBinding
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckAdapter : ListAdapter<CardDeck,
        ArtifactDeckAdapter.ViewHolder>(ArtifactDeckDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardDeck = getItem(position)
        holder.apply {
            bind(createOnClickListener(cardDeck, holder.itemView.context), cardDeck)
            //itemView.tag = plant
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArtifactFragDeckItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(cardDeck: CardDeck, context: Context): View.OnClickListener {
        return View.OnClickListener {
            ArtifactDeckDetailActivity.launch(context as Activity, cardDeck)
        }
    }

    class ViewHolder(
        private val binding: ArtifactFragDeckItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: CardDeck) {
            binding.apply {
                clickListener = listener
                cardDeck = item
                executePendingBindings()
            }
        }
    }
}