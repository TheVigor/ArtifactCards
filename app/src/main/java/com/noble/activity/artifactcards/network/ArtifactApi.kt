package com.noble.activity.artifactcards.network

import com.noble.activity.artifactcards.model.card.CardSetInfo
import com.noble.activity.artifactcards.model.card.CardSets
import com.noble.activity.artifactcards.model.price.CardPrices
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ArtifactApi {

    @GET("{cardset_id}")
    fun getCardSetInfo(@Path("cardset_id") cardSetId: String): Single<CardSetInfo>

    @GET
    fun getCardSet(@Url url: String): Single<CardSets>

    @GET
    fun getCardPrice(@Url url: String): Single<CardPrices>




}