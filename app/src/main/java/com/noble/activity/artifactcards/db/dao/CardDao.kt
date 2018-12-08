package com.noble.activity.artifactcards.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.noble.activity.artifactcards.model.Card
import io.reactivex.Flowable

@Dao
interface CardDao {

    @Query("SELECT * FROM cards ORDER BY cardId DESC")
    fun loadAllArtifactCardList(): Flowable<List<Card>>

    @Query("SELECT * FROM cards WHERE cardType = :type ORDER BY cardId DESC")
    fun loadArtifactCardListByType(type: String): Flowable<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtifactCardList(newsList: List<Card>)

    @Query("SELECT * FROM cards WHERE cardId = :cardId")
    fun getCardById(cardId: String): LiveData<Card>
}