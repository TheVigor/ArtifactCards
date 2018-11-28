package com.noble.activity.artifactcards

import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable
import io.reactivex.Single

interface IRemoteDataSource {

    abstract fun getCardsList(token: String, page: Int, id: Int): Flowable<HttpResult<List<Card>>>
}