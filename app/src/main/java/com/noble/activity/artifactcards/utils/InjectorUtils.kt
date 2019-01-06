package com.noble.activity.artifactcards.utils

import android.support.v4.os.ConfigurationCompat
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.artifact.detail.ArtifactCardDetailViewModelFactory
import com.noble.activity.artifactcards.deck.ArtifactDeckViewModelFactory

object InjectorUtils {
    fun provideArtifactCardDetailViewModelFactory(
        cardId: String,
        cardName: String,
        firstRefId: String,
        secondRefId: String,
        thirdRefId: String
    ): ArtifactCardDetailViewModelFactory {
        val locale = ConfigurationCompat.getLocales(app.resources.configuration)[0]
        return ArtifactCardDetailViewModelFactory(
            ArtifactRepository.get(),
            cardId,
            cardName,
            firstRefId,
            secondRefId,
            thirdRefId,
            locale.language
        )
    }

    fun provideArtifactDeckViewModelFactory(): ArtifactDeckViewModelFactory {
        return ArtifactDeckViewModelFactory(ArtifactRepository.get())
    }


}