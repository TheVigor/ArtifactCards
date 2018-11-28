package com.noble.activity.artifactcards.source.remote

import com.noble.activity.artifactcards.model.Card
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable

interface IRemoteDataSource {

    abstract fun getCardsList(token: String, page: Int, id: Int): Flowable<HttpResult<List<Card>>>
}