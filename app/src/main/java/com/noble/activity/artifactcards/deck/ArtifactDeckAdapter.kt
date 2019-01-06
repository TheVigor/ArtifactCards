package com.noble.activity.artifactcards.deck

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckItemBinding
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckAdapter : ListAdapter<Card,
        ArtifactDeckAdapter.ViewHolder>(ArtifactDeckDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.apply {
            bind(createOnClickListener(card.cardId), card)
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

    private fun createOnClickListener(drillDocId: Int): View.OnClickListener {
        return View.OnClickListener {
            //val direction = DrillDocFragmentDirections.ActionDrillDocFragmentToDrillDocDetailFragment(drillDocId)
            //it.findNavController().navigate(direction)
        }
    }

    class ViewHolder(
        private val binding: ArtifactFragDeckItemBinding
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