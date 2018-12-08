package com.noble.activity.artifactcards.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.model.Card

class ArtifactCardDetailViewModel(
    artifactRepository: ArtifactRepository,
    private val cardId: String
) : ViewModel() {
    val card: LiveData<Card>

    init {
        card = artifactRepository.getCardById(cardId)
    }
}