package com.noble.activity.artifactcards

import com.noble.activity.artifactcards.db.ArtifactAppDatabase
import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.network.ArtifactClient
import com.noble.activity.artifactcards.source.remote.IRemoteDataSource
import com.ruzhan.lion.model.HttpResult
import io.reactivex.Flowable

class ArtifactRepository private constructor() : IRemoteDataSource {

    private val remoteDataSource: IRemoteDataSource
    private val artifactAppDatabase: ArtifactAppDatabase

    init {
        remoteDataSource = RemoteDataSourceImpl(ArtifactClient.get()!!)
        artifactAppDatabase = ArtifactAppDatabase.get(App.get())
    }

    fun loadNewsList(): Flowable<List<Card>> {
        return artifactAppDatabase.cardDao().loadNewsList()
    }

    fun insertNewsList(newsList: List<Card>) {
        artifactAppDatabase.cardDao().insertNewsList(newsList)
    }

    override fun getCardsList(token: String, page: Int, id: Int): Flowable<HttpResult<List<Card>>> {
        return remoteDataSource.getCardsList(token, page, id)
    }

    companion object {

        private var INSTANCE: ArtifactRepository? = null

        fun get(): ArtifactRepository {
            if (INSTANCE == null) {
                synchronized(ArtifactRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ArtifactRepository()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}