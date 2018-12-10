package com.noble.activity.artifactcards.artifact.detail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.artifactcards.ArtifactRepository

class ArtifactCardDetailViewModelFactory(
    private val artifactRepository: ArtifactRepository,
    private val cardId: String,
    private val firstRefId: String,
    private val secondRefId: String,
    private val thirdRefId: String,
    private val locale: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtifactCardDetailViewModel(
            artifactRepository,
            cardId,
            firstRefId,
            secondRefId,
            thirdRefId,
            locale
        ) as T
    }
}