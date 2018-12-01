package com.noble.activity.artifactcards

import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.model.CardSetInfo
import com.noble.activity.artifactcards.model.CardSets
import com.noble.activity.artifactcards.network.ArtifactApi
import com.noble.activity.artifactcards.source.remote.IRemoteDataSource
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl(private val artifactApi: ArtifactApi) :
    IRemoteDataSource {

    override fun getRemoteCardSet(url: String): Flowable<CardSets> {
        return artifactApi.getCardSet(url)
            .subscribeOn(Schedulers.io())
    }

    override fun getRemoteCardSetInfo(cardSetId: String): Flowable<CardSetInfo> {
        return artifactApi.getCardSetInfo(cardSetId)
            .subscribeOn(Schedulers.io())
    }




}