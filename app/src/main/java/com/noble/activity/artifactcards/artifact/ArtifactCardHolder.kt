package com.noble.activity.artifactcards.artifact

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
        itemView.title_tv.typeface = FontHelper.get().getLightTypeface()
        itemView.category_tv.typeface = FontHelper.get().getLightTypeface()
        itemView.comment_tv.typeface = FontHelper.get().getLightTypeface()

        itemView.root_cv.setOnClickListener { listener.onItemClick(adapterPosition, card, itemView) }
    }

    fun bind(bean: Card) {
        card = bean

        itemView.title_tv.text = card.cardName?.russian
        itemView.category_tv.text = card.cardText?.russian

        //val commentStr = String.format(itemView.resources
        //    .getString(R.string.awaker_article_comment_count), bean.comment)


        itemView.comment_tv.text = card.rarity

        card.miniImage?.default?.let {
            ImageLoader.get().load(itemView.icon_iv, it)
        }
    }
}