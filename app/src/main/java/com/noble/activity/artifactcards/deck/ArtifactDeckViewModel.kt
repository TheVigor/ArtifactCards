package com.noble.activity.artifactcards.deck

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.model.card.Card

class ArtifactDeckViewModel internal constructor(
    private val artifactRepository: ArtifactRepository
) : ViewModel() {
    private val artifactDeckList = MediatorLiveData<List<Card>>()

    init {
        val actualArtifactDeckList = artifactRepository.getCards()

        artifactDeckList.addSource(actualArtifactDeckList, artifactDeckList::setValue)
    }

    fun getDecks() = artifactDeckList
}