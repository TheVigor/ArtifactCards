package com.noble.activity.artifactcards.artifact

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.utils.PAGE_SIZE
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.R.id.title_tv
import com.noble.activity.artifactcards.databinding.ArtifactItemNewListGridBinding
import com.noble.activity.artifactcards.imageloader.ImageLoader
import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.listener.OnItemClickListener
import com.ruzhan.lion.ui.LoadMoreHolder
import kotlinx.android.synthetic.main.artifact_item_new_list_grid.view.*
import java.util.ArrayList

class ArtifactCardAdapter : ListAdapter<Card,
        ArtifactCardAdapter.ViewHolder>(ArtifactCardDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = getItem(position)
        holder.apply {
            bind(createOnClickListener(card.cardId), card)
            //itemView.tag = plant
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ArtifactItemNewListGridBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    private fun createOnClickListener(cardId: Int): View.OnClickListener {
        return View.OnClickListener {
        }
    }


    class ViewHolder(
        private val binding: ArtifactItemNewListGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Card) {
            binding.apply {
                clickListener = listener
                card = item

                binding.titleTv.text = item.cardName?.russian
                binding.categoryTv.text = item.cardText?.russian
                binding.commentTv.text = item.rarity

                item.miniImage?.default?.let {
                    ImageLoader.get().load(binding.iconIv, it)
                }

                executePendingBindings()
            }
        }
    }

}
