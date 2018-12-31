package com.noble.activity.artifactcards.artifact.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.noble.activity.artifactcards.imageloader.ImageLoader
import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.utils.OnItemClickListener
import com.noble.activity.artifactcards.utils.setTextFromHtml
import kotlinx.android.synthetic.main.artifact_item_item.view.*

class ArtifactCardItemHolder(itemView: View, private var listener: OnItemClickListener<Card>)
    : RecyclerView.ViewHolder(itemView) {

    private lateinit var card: Card

    init {
        itemView.root_cv.setOnClickListener {
            listener.onItemClick(adapterPosition, card, itemView)
        }
    }

    fun bind(bean: Card, locale: String) {
        card = bean

        itemView.card_name.setTextFromHtml(card.getNameByLocale(locale))

        itemView.card_rarity_image.setImageDrawable(card.getRarityIcon())
        itemView.card_rarity.text = card.getRarityByLocale()

        itemView.card_gold_value.text = card.getGold()

        card.getMiniImageByLocale()?.let {
            ImageLoader.get().load(itemView.icon_iv, it)
        }
    }
}