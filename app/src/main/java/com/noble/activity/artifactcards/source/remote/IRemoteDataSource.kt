package com.noble.activity.artifactcards.source.remote

import com.noble.activity.artifactcards.model.CardSetInfo
import com.noble.activity.artifactcards.model.CardSets
import io.reactivex.Single

interface IRemoteDataSource {

    fun getRemoteCardSetInfo(cardSetId: String): Single<CardSetInfo>

    fun getRemoteCardSet(url: String): Single<CardSets>

}