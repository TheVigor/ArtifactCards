package com.noble.activity.artifactcards.source.remote

import com.noble.activity.artifactcards.model.card.CardSetInfo
import com.noble.activity.artifactcards.model.card.CardSets
import com.noble.activity.artifactcards.model.price.CardPrices
import io.reactivex.Single

interface IRemoteDataSource {
    fun getRemoteCardSetInfo(cardSetId: String): Single<CardSetInfo>
    fun getRemoteCardSet(url: String): Single<CardSets>

    fun getRemoteCardPrice(url: String): Single<CardPrices>
}