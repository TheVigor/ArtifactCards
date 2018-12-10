package com.noble.activity.artifactcards.network

import com.noble.activity.artifactcards.model.CardSetInfo
import com.noble.activity.artifactcards.model.CardSets
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ArtifactApi {

    @GET("{cardset_id}")
    fun getCardSetInfo(@Path("cardset_id") cardSetId: String): Single<CardSetInfo>

    @GET
    fun getCardSet(@Url url: String): Single<CardSets>




}