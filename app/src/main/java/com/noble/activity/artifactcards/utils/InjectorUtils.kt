package com.noble.activity.artifactcards.utils

import android.content.Context
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.viewmodels.ArtifactCardDetailViewModelFactory

object InjectorUtils {
    fun provideArtifactCardDetailViewModelFactory(
        cardId: String
    ): ArtifactCardDetailViewModelFactory {
        return ArtifactCardDetailViewModelFactory(ArtifactRepository.get(), cardId)
    }
}