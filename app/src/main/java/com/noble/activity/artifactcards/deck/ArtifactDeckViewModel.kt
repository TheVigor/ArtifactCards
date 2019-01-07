package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.deck.model.CardDeck
import com.noble.activity.artifactcards.deck.model.Deck
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckViewModel internal constructor(
    private val artifactRepository: ArtifactRepository
) : ViewModel() {
    val artifactDeckList = MutableLiveData<MutableList<CardDeck>>()

    init {
        artifactDeckList.value = mutableListOf()
    }

    fun getDecks() = artifactRepository.getCards()

    fun getDeck(ids: List<Int>) = artifactRepository.getDeck(ids)
}