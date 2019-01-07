package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.databinding.ArtifactFragDeckBinding
import com.noble.activity.artifactcards.deck.coder.ArtifactDeckDecoder
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.InjectorUtils

class ArtifactDeckFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ArtifactDeckFragment()
    }

    private lateinit var artifactDeckViewModel: ArtifactDeckViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ArtifactFragDeckBinding.inflate(inflater, container, false)

        val factory = InjectorUtils.provideArtifactDeckViewModelFactory()

        artifactDeckViewModel = ViewModelProviders
            .of(this, factory)
            .get(ArtifactDeckViewModel::class.java)

        val adapter = ArtifactDeckAdapter()
        binding.recyclerView.adapter = adapter


        val decoder = ArtifactDeckDecoder()
        val decodedDeck = decoder.decode("ADCJQUQI30zuwEYg2ABeF1Bu94BmWIBTEkLtAKlAZakAYmHh0JsdWUvUmVkIEV4YW1wbGU_")

        subscribeUi(adapter, decodedDeck)
        return binding.root
    }

    private fun subscribeUi(adapter: ArtifactDeckAdapter, deck: Deck) {
        artifactDeckViewModel.getDeck(deck.getIds()).observe(viewLifecycleOwner, Observer { cards ->
            if (cards != null) {
                val deck = CardDeck(name = deck.name, heroes = cards, cards = cards)
                artifactDeckViewModel.artifactDeckList.value?.add(deck)
                artifactDeckViewModel.artifactDeckList.value = artifactDeckViewModel.artifactDeckList.value
            }
        })

        artifactDeckViewModel.artifactDeckList.observe(viewLifecycleOwner, Observer { decks ->
            if (decks != null) {
                adapter.submitList(null)
                adapter.submitList(decks)
            }
        })

    }




}