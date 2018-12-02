package com.noble.activity.artifactcards.artifact

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.View
import com.noble.activity.artifactcards.imageloader.ImageLoader
import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.helper.FontHelper
import com.ruzhan.lion.listener.OnItemClickListener
import kotlinx.android.synthetic.main.artifact_item_new_list_grid.view.*

class ArtifactCardHolder(itemView: View, private var listener: OnItemClickListener<Card>)
    : RecyclerView.ViewHolder(itemView) {

    private lateinit var card: Card

    init {
        itemView.card_name.typeface = FontHelper.get().getLightTypeface()
        itemView.card_color.typeface = FontHelper.get().getLightTypeface()
        itemView.card_rarity.typeface = FontHelper.get().getLightTypeface()

        itemView.root_cv.setOnClickListener { listener.onItemClick(adapterPosition, card, itemView) }
    }

    fun bind(bean: Card) {
        card = bean

        itemView.card_name.text = card.cardName.russian

        itemView.card_color.text = card.getColorName()
        itemView.card_color.setTextColor(card.getTextColor())


        itemView.card_rarity.text = card.rarity


        card.miniImage?.default?.let {
            ImageLoader.get().load(itemView.icon_iv, it)
        }
    }
}