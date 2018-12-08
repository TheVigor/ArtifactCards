package com.noble.activity.artifactcards.viewmodels

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.noble.activity.artifactcards.ArtifactRepository

class ArtifactCardDetailViewModelFactory(
    private val artifactRepository: ArtifactRepository,
    private val cardId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ArtifactCardDetailViewModel(
            artifactRepository,
            cardId
        ) as T
    }
}