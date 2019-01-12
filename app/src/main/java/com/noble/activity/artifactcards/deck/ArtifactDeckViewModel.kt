package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.*
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.MainActivity
import com.noble.activity.artifactcards.R
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.app.decksPrefs
import com.noble.activity.artifactcards.deck.coder.ArtifactDeckDecoder
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.utils.HERO_CARD_TYPE
import com.noble.activity.artifactcards.utils.runOnIoThread
import com.noble.activity.artifactcards.utils.showToast
import java.lang.Exception

class ArtifactDeckViewModel internal constructor(
    private val artifactRepository: ArtifactRepository
) : ViewModel() {
    val artifactDeckList = MutableLiveData<MutableList<CardDeck>>()

    init {
        artifactDeckList.value = mutableListOf()
    }

    fun getDecks() = artifactRepository.getCards()
    fun getDeck(ids: List<Int>) = artifactRepository.getDeck(ids)

    fun loadDeckByCode(deckCode: String, lifecycleOwner: LifecycleOwner, needSave: Boolean) {
        runOnIoThread {
            var decodedDeck = Deck(name = "", heroes = listOf(), cards = listOf())
            try {
                val decoder = ArtifactDeckDecoder()
                decodedDeck = decoder.decode(deckCode)
            } catch (e: Exception) {}

            val cardDeck = getDeck(decodedDeck.getIds())
            cardDeck.observe(lifecycleOwner, Observer { deck ->
                if (deck != null) {

                    val heroes = deck.filter { it.cardType == HERO_CARD_TYPE }
                    val notHeroes = deck.filter { it.cardType != HERO_CARD_TYPE }

                    if (heroes.size == 5) {
                        val references: List<Int> = heroes.map { it.references[0].cardId }

                        val mapTurnCount = decodedDeck.getMap()
                        references.forEach { mapTurnCount[it] = 3 }

                        val refCards = getDeck(references)
                        refCards.observe(lifecycleOwner, Observer { refs ->
                            if (refs != null) {
                                artifactDeckList.value?.add(
                                    CardDeck(
                                        decodedDeck.name,
                                        heroes,
                                        notHeroes + refs,
                                        mapTurnCount
                                    )
                                )

                                artifactDeckList.postValue(artifactDeckList.value)

                                if (needSave) {
                                    val decks = decksPrefs.refreshCards
                                    decks.add(deckCode)
                                    decksPrefs.refreshCards = mutableSetOf()
                                    decksPrefs.refreshCards = decks
                                }


                            }
                        })
                    } else {
                        app.showToast(app.getString(R.string.deck_parsing_error))
                    }
                }
            })
        }
    }

}