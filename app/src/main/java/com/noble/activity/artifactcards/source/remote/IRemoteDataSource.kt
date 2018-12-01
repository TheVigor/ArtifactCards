package com.noble.activity.artifactcards.source.remote

import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.model.CardSetInfo
import com.noble.activity.artifactcards.model.CardSets
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import io.reactivex.Single

interface IRemoteDataSource {

    fun getRemoteCardSetInfo(cardSetId: String): Flowable<CardSetInfo>

    fun getRemoteCardSet(url: String): Flowable<CardSets>

}