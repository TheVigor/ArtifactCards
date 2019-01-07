package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.MutableLiveData
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
import com.noble.activity.artifactcards.utils.HERO_CARD_TYPE
import com.noble.activity.artifactcards.utils.InjectorUtils
import com.noble.activity.artifactcards.utils.runOnIoThread

class ArtifactDeckFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ArtifactDeckFragment()

        private val deckCodes = listOf<String>(
            "ADCJQUQI30zuwEYg2ABeF1Bu94BmWIBTEkLtAKlAZakAYmHh0JsdWUvUmVkIEV4YW1wbGU_",
            "ADCJRwSJX2Dc7wBEAN4XUFBcN0BQmQBQWABRCgBCgN0AWUBbQFDbwEISEJsdWUvQmxhY2sgQ29udHJvbA__",
            "ADCJcURIH0De7sBKAGQeF1BQWbdAVhHRwFIMQIECG0CTgIfRlBCdQFSZWQvR3JlZW4gQnJhd2xlcg__",
            "ADCJY8UNrgC0QVIh8kEubwCZQKTkBasAYgHhpmmAZOBKEJsYWNrL0JsdWUpIFBheWJhY2s_",
            "ADCJRkQfrgCCQ0LlHpdpt0BhoaPhJGgAo1SU6UCowNCL0cgUGF1cGVyIEFnZ3Jv"
        )

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
        subscribeUi(adapter)

        runOnIoThread {
            deckCodes.forEach {
                runOnIoThread {
                    val decoder = ArtifactDeckDecoder()
                    val decodedDeck = decoder.decode(it)

                    val cardDeck = artifactDeckViewModel.getDeck(decodedDeck.getIds())
                    cardDeck.observe(viewLifecycleOwner, Observer { deck ->
                        if (deck != null) {

                            val heroes = deck.filter { it.cardType == HERO_CARD_TYPE }
                            val notHeroes = deck.filter { it.cardType != HERO_CARD_TYPE}


                            val references: List<Int> = heroes.map { it.references[0].cardId }

                            val refCards = artifactDeckViewModel.getDeck(references)
                            refCards.observe(viewLifecycleOwner, Observer { refs ->
                                if (refs != null) {
                                    artifactDeckViewModel.artifactDeckList.value?.add(CardDeck(decodedDeck.name, heroes, notHeroes + refs))
                                    artifactDeckViewModel.artifactDeckList.postValue(artifactDeckViewModel.artifactDeckList.value)
                                }
                            })
                        }
                    })
                }
            }
        }

        return binding.root
    }

    private fun subscribeUi(adapter: ArtifactDeckAdapter) {
        artifactDeckViewModel.artifactDeckList.observe(viewLifecycleOwner, Observer { decks ->
            if (decks != null) {
                //adapter.submitList(null)
                adapter.submitList(decks)
                adapter.notifyDataSetChanged() // because fuck you submit list
            }
        })

    }




}