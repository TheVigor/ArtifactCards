package com.noble.activity.artifactcards.artifact.main

import android.support.v7.widget.RecyclerView
import android.view.View
import com.noble.activity.artifactcards.imageloader.ImageLoader
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.OnItemClickListener
import com.noble.activity.artifactcards.utils.setTextFromHtml
import kotlinx.android.synthetic.main.artifact_item_spell.view.*

class ArtifactCardSpellHolder(itemView: View, private var listener: OnItemClickListener<Card>)
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

        itemView.card_color.text = card.getColorNameWithTr()
        itemView.card_color.setTextColor(card.getTextColor())

        itemView.card_rarity_image.setImageDrawable(card.getRarityIcon())
        itemView.card_rarity.text = card.getRarityByLocale()

        itemView.card_mana_value.text = card.getMana()


        card.getMiniImageByLocale()?.let {
            ImageLoader.get().load(itemView.icon_iv, it)
        }
    }
}