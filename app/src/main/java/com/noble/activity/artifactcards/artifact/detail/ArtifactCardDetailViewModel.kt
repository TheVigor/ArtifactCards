package com.noble.activity.artifactcards.artifact.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.model.Card

class ArtifactCardDetailViewModel(
    artifactRepository: ArtifactRepository,
    cardId: String,
    firstRefId: String,
    secondRefId: String,
    thirdRefId: String,
    loc: String
) : ViewModel() {
    val card: LiveData<Card> = artifactRepository.getCardById(cardId)
    val locale: String = loc

    val firstRefCard: LiveData<Card> = artifactRepository.getCardById(firstRefId)
    val secondRefCard: LiveData<Card> = artifactRepository.getCardById(secondRefId)
    val thirdRefCard: LiveData<Card> = artifactRepository.getCardById(thirdRefId)

}