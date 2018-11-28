package com.noble.activity.artifactcards

import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.network.ArtifactApi
import com.noble.activity.artifactcards.source.remote.IRemoteDataSource
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

class RemoteDataSourceImpl(private val artifactApi: ArtifactApi) :
    IRemoteDataSource {

    override fun getCardsList(token: String, page: Int, id: Int): Flowable<HttpResult<List<Card>>> {
        return artifactApi.getCardsList(token, page, id)
            .subscribeOn(Schedulers.io())
    }



}