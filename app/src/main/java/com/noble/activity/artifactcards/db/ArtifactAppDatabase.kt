package com.noble.activity.artifactcards.db

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.noble.activity.artifactcards.app.App
import com.noble.activity.artifactcards.converter.*
import com.noble.activity.artifactcards.model.Card
import com.noble.activity.artifactcards.db.dao.CardDao

@Database(entities = [Card::class], version = 1, exportSchema = false)
@TypeConverters(
    ReferenceConverter::class,
    MiniImageConverter::class,
    CardNameConverter::class,
    CardTextConverter::class,
    LargeImageConverter::class,
    IngameImageConverter::class)
abstract class ArtifactAppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao

    companion object {
        @VisibleForTesting
        val DATABASE_NAME = "artifact-db"

        private var INSTANCE: ArtifactAppDatabase? = null

        operator fun get(context: App): ArtifactAppDatabase {
            if (INSTANCE == null) {
                synchronized(ArtifactAppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context, ArtifactAppDatabase::class.java,
                            DATABASE_NAME
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}