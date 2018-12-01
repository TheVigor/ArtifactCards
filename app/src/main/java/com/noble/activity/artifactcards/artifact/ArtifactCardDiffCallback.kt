package com.noble.activity.artifactcards.artifact

import android.support.v7.util.DiffUtil
import com.noble.activity.artifactcards.model.Card

class ArtifactCardDiffCallback : DiffUtil.ItemCallback<Card>() {

    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.cardId == newItem.cardId
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}