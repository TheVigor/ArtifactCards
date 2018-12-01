package com.noble.activity.artifactcards.network

import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.model.CardSetInfo
import com.noble.activity.artifactcards.model.CardSets
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface ArtifactApi {

    @GET("{cardset_id}")
    fun getCardSetInfo(@Path("cardset_id") cardSetId: String): Flowable<CardSetInfo>

    @GET
    fun getCardSet(@Url url: String): Flowable<CardSets>


    @FormUrlEncoded
    @POST("news/getNewsAll")
    fun getCardsList(
        @Field("access_token") token: String,
        @Field("page") page: Int, @Field("id") id: Int
    ): Flowable<HttpResult<List<Card>>>


}