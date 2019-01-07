package com.noble.activity.artifactcards

import com.noble.activity.artifactcards.app.app
import com.noble.activity.artifactcards.db.ArtifactAppDatabase
import com.noble.activity.artifactcards.model.card.Card
import com.noble.activity.artifactcards.model.card.CardSetInfo
import com.noble.activity.artifactcards.model.card.CardSets
import com.noble.activity.artifactcards.model.price.CardPrices
import com.noble.activity.artifactcards.network.ArtifactClient
import com.noble.activity.artifactcards.source.remote.IRemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Single

class ArtifactRepository private constructor() : IRemoteDataSource {

    private val remoteDataSource: IRemoteDataSource
    private val artifactAppDatabase: ArtifactAppDatabase

    init {
        remoteDataSource = RemoteDataSourceImpl(ArtifactClient.get()!!)
        artifactAppDatabase = ArtifactAppDatabase.get(app)
    }

    override fun getRemoteCardPrice(url: String): Single<CardPrices> {
        return remoteDataSource.getRemoteCardPrice(url)
    }

    override fun getRemoteCardSetInfo(cardSetId: String): Single<CardSetInfo> {
        return remoteDataSource.getRemoteCardSetInfo(cardSetId)
    }

    override fun getRemoteCardSet(url: String): Single<CardSets> {
        return remoteDataSource.getRemoteCardSet(url)
    }

    fun loadAllArtifactCardList(): Flowable<List<Card>> {
        return artifactAppDatabase.cardDao().loadAllArtifactCardList()
    }

    fun loadArtifactCardListByType(type: String): Flowable<List<Card>> {
        return artifactAppDatabase.cardDao().loadArtifactCardListByType(type)
    }

    fun insertArtifactCardList(newsList: List<Card>) {
        artifactAppDatabase.cardDao().insertArtifactCardList(newsList)
    }

    fun getCardById(cardId: String) =
        artifactAppDatabase.cardDao().getCardById(cardId)

    fun getCards() = artifactAppDatabase.cardDao().getCards()

    fun getDeck(ids: List<Int>) = artifactAppDatabase.cardDao().getDeck(ids)

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