package com.noble.activity.artifactcards.utils

import android.content.Context
import android.support.v4.os.ConfigurationCompat
import com.noble.activity.artifactcards.App
import com.noble.activity.artifactcards.ArtifactRepository
import com.noble.activity.artifactcards.viewmodels.ArtifactCardDetailViewModelFactory

object InjectorUtils {
    fun provideArtifactCardDetailViewModelFactory(
        cardId: String,
        firstRefId: String,
        secondRefId: String,
        thirdRefId: String
    ): ArtifactCardDetailViewModelFactory {
        val locale = ConfigurationCompat.getLocales(App.get()?.resources?.configuration)[0]
        return ArtifactCardDetailViewModelFactory(
            ArtifactRepository.get(),
            cardId,
            firstRefId,
            secondRefId,
            thirdRefId,
            locale.language)
    }
}