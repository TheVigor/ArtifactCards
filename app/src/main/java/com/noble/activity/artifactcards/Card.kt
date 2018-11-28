package com.noble.activity.artifactcards

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import io.reactivex.annotations.NonNull

@Entity(tableName = "cards")
data class Card constructor(
    @NonNull
    @PrimaryKey
    var id: String,
    var title: String = "",
    var description: String = "",
    var url: String = ""
)