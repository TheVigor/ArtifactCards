package com.noble.activity.artifactcards.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.noble.activity.artifactcards.model.Card
import io.reactivex.Flowable

@Dao
interface CardDao {

    @Query("SELECT * FROM cards ORDER BY cardId DESC")
    fun loadArtifactCardList(): Flowable<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtifactCardList(newsList: List<Card>)
}