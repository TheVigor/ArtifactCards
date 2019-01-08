package com.noble.activity.artifactcards.deck

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.artifact.detail.ArtifactCardDetailActivity
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckBinding
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckDetailBinding
import com.noble.activity.artifactcards.model.card.Card
import kotlinx.android.synthetic.main.artifact_frag_deck_detail.*

class ArtifactDeckDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(): ArtifactDeckDetailFragment {
            return ArtifactDeckDetailFragment()
        }
    }

    //private lateinit var artifactCardDetailViewModel: ArtifactCardDetailViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = ArtifactFragDeckDetailBinding.inflate(inflater, container, false)
        binding.cardDeck = App.cardDeck

        binding.firstHero.setOnClickListener {
            clickByHero(App.cardDeck.heroes[0])
        }

        binding.secondHero.setOnClickListener {
            clickByHero(App.cardDeck.heroes[1])
        }

        binding.thirdHero.setOnClickListener {
            clickByHero(App.cardDeck.heroes[2])
        }

        binding.fourthHero.setOnClickListener {
            clickByHero(App.cardDeck.heroes[3])
        }

        binding.fifthHero.setOnClickListener {
            clickByHero(App.cardDeck.heroes[4])
        }



        val adapter = ArtifactDeckDetailAdapter()
        binding.recyclerView.adapter = adapter
        adapter.submitList(App.cardDeck.cards.sortedBy { it.manaCost })


        return binding.root

    }

    private fun clickByHero(cardDeck: Card) {
            var firstRef = 0
            if (cardDeck.references.isNotEmpty()) {
                firstRef = cardDeck.references[0].cardId
            }

            ArtifactCardDetailActivity.launch(activity!!,
                cardDeck.cardId.toString(),
                cardDeck.cardName.english!!,
                firstRef.toString(),
                0.toString(),
                0.toString())
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar(toolbar_settings)
    }

    private fun setToolbar(toolbar: Toolbar?) {
        if (toolbar == null) {
            return
        }
        val activity = activity as AppCompatActivity?
        activity!!.setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener(View.OnClickListener { activity.onBackPressed() })

        val actionBar = activity.supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
}