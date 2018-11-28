package com.noble.activity.artifactcards

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface CardDao {

    @Query("SELECT * FROM cards ORDER BY id DESC")
    fun loadNewsList(): Flowable<List<Card>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsList(newsList: List<Card>)
}