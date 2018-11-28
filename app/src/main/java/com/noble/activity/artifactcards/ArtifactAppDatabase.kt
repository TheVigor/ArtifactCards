package com.noble.activity.artifactcards

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.ruzhan.lion.db.converter.RoomDataConverter

@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class ArtifactAppDatabase : RoomDatabase() {

    private val isDatabaseCreated = MutableLiveData<Boolean>()

    val databaseCreated: LiveData<Boolean>
        get() = isDatabaseCreated

    abstract fun cardDao(): CardDao

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            isDatabaseCreated.postValue(true)
        }
    }

    companion object {

        @VisibleForTesting
        val DATABASE_NAME = "artifact-db"

        private var INSTANCE: ArtifactAppDatabase? = null


        operator fun get(context: Context): ArtifactAppDatabase {
            if (INSTANCE == null) {
                synchronized(ArtifactAppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context, ArtifactAppDatabase::class.java, DATABASE_NAME)
                            .build()
                        INSTANCE!!.updateDatabaseCreated(context)
                    }
                }
            }
            return INSTANCE!!
        }
    }
}