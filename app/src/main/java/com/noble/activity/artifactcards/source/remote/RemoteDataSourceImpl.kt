package com.noble.activity.artifactcards

import com.noble.activity.artifactcards.model.card.CardSetInfo
import com.noble.activity.artifactcards.model.card.CardSets
import com.noble.activity.artifactcards.model.price.CardPrices
import com.noble.activity.artifactcards.network.ArtifactApi
import com.noble.activity.artifactcards.source.remote.IRemoteDataSource
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl(private val artifactApi: ArtifactApi) :
    IRemoteDataSource {
    override fun getRemoteCardPrice(url: String): Single<CardPrices> {
        return artifactApi.getCardPrice(url)
            .subscribeOn(Schedulers.io())
    }

    override fun getRemoteCardSet(url: String): Single<CardSets> {
        return artifactApi.getCardSet(url)
            .subscribeOn(Schedulers.io())
    }

    override fun getRemoteCardSetInfo(cardSetId: String): Single<CardSetInfo> {
        return artifactApi.getCardSetInfo(cardSetId)
            .subscribeOn(Schedulers.io())
    }




}