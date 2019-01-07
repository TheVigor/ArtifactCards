package com.noble.activity.artifactcards.deck

import android.support.v7.util.DiffUtil
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckDiffCallback : DiffUtil.ItemCallback<CardDeck>() {

    override fun areItemsTheSame(oldItem: CardDeck, newItem: CardDeck): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CardDeck, newItem: CardDeck): Boolean {
        return oldItem == newItem
    }
}