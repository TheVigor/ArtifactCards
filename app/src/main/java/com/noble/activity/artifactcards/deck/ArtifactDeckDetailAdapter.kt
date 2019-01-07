package com.noble.activity.artifactcards.deck

import android.app.Activity
import android.content.Context
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckDetailItemBinding
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckDetailAdapter : ListAdapter<Card,
        ArtifactDeckDetailAdapter.ViewHolder>(ArtifactDeckDetailDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.apply {
            bind(createOnClickListener(card, holder.itemView.context), card)
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

        fun bind(listener: View.OnClickListener, item: Card) {
            binding.apply {
                clickListener = listener
                card = item
                executePendingBindings()
            }
        }
    }
}