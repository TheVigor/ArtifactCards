package com.noble.activity.artifactcards.deck

import android.support.v7.util.DiffUtil
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckDiffCallback : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardId == newItem.cardId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}