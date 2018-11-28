package com.noble.activity.artifactcards.network

import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ArtifactApi {

    @FormUrlEncoded
    @POST("news/getNewsAll")
    fun getCardsList(
        @Field("access_token") token: String,
        @Field("page") page: Int, @Field("id") id: Int
    ): Flowable<HttpResult<List<Card>>>


}